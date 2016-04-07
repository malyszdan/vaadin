package org.test;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.client.ui.Grid;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.daniel.ug.bi.domain.Car;
import pl.daniel.ug.bi.domain.User;
import pl.daniel.ug.bi.domain.Car.Brand;
import pl.daniel.ug.bi.services.CarService;
import pl.daniel.ug.bi.services.UserService;
import pl.daniel.ug.bi.services.UserService.BadPassEx;
import pl.daniel.ug.bi.services.UserService.NoSuchUserEx;

import com.vaadin.data.*;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.test.MyAppWidgetset")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		GridLayout gird = new GridLayout(3, 2);
		gird.setSizeFull();
		RegisterWindow regWin = new RegisterWindow();
		LoginWindow lgnWin = new LoginWindow();
		gird.addComponent(regWin, 0, 0);
		gird.setComponentAlignment(regWin, Alignment.TOP_LEFT);
		gird.addComponent(lgnWin, 1, 0);
		gird.setComponentAlignment(lgnWin, Alignment.TOP_RIGHT);
		setContent(gird);
		//setContent(new HorizontalLayout(new RegisterWindow(), new LoginWindow()));
		
	}

	class MainView extends CustomComponent {

		BeanItemContainer<Car> cars = new BeanItemContainer<>(Car.class);

		public MainView() {

			GridLayout root = new GridLayout(3,3);
			root.setSizeFull();
			
			Table carsTable = new Table();
			cars.addAll(CarService.getInstance().getAllCars());
			carsTable.setContainerDataSource(cars);
			carsTable.setSelectable(true);
			carsTable.setColumnHeaders("Marka", "Przebieg", "Dostępność",
					"Model", "Rok Produkcji");
			carsTable.setPageLength(carsTable.size());
			
			Button removeCarButton = new Button("Usuń auto");
			removeCarButton.addClickListener(new ClickListener() {	
				@Override
				public void buttonClick(ClickEvent event) {
					 Object selectedRow = carsTable.getValue();
					 CarService.getInstance().remove((Car) selectedRow);
					 cars.removeItem(selectedRow);
				}
			});
			
			Button changeStatusButton = new Button("Zarezerwuj");
			changeStatusButton.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					Object selectedRow = carsTable.getValue();
					 Car car = (Car) selectedRow;
					 car.setFree(false);
					 cars.removeAllItems();
					 cars.addAll(CarService.getInstance().getAllCars());
				}
			});
			
			VerticalLayout formLayout = new VerticalLayout(); //add car form layout
			FormLayout form = new FormLayout();
			Car newCar = new Car("");
			FieldGroup fieldGroup = new FieldGroup(new BeanItem<Car>(newCar));
			Field<?> model= fieldGroup.buildAndBind("Model", "model");
			model.setRequired(true);
			Field<?> distance = fieldGroup.buildAndBind("Przebieg", "distance");
			distance.setRequired(true);
			distance.addValidator(new DoubleRangeValidator("Coś tu nie gra", 0.1, 100000000000.0));
			Field<?> brand = fieldGroup.buildAndBind("Marka", "brand");
			brand.setRequired(true);
			Field<?> yearField = fieldGroup.buildAndBind("Rok Produkcji", "yearProd");
			yearField.setRequired(true);
			yearField.addValidator(new IntegerRangeValidator("Czy to Wehikuł czasu? ", 1900, 2016));
			form.addComponents(model, distance, brand, yearField);
		
			Button addCarButton = new Button("Dodaj");
			addCarButton.addClickListener(event -> {
				
				try {
					distance.validate();
					yearField.validate();
					fieldGroup.commit();
					CarService.getInstance().addCar(newCar.copy());
					cars.removeAllItems();
					cars.addAll(CarService.getInstance().getAllCars());
				} catch(InvalidValueException e){
					Notification.show(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			form.addComponent(addCarButton);
			formLayout.addComponent(form);
			root.addComponents(carsTable, removeCarButton, changeStatusButton, formLayout);
			setCompositionRoot(root);
		}

	}

	class LoginWindow extends CustomComponent {

		private User user = new User("","","");
		private FieldGroup fieldGroup = new FieldGroup(new BeanItem<User>(user));

		public LoginWindow() {
			FormLayout formLayout = new FormLayout();			
			final Field<?> loginField = fieldGroup.buildAndBind("Login", "name");
			formLayout.addComponent(loginField);
			Button loginButton = new Button("Zaloguj");
			Field<?> passFIeld = fieldGroup.buildAndBind("Hasło","pass");	
			formLayout.addComponent(new PasswordField("Hasło", passFIeld));
			formLayout.addComponent(loginButton);
			
			loginButton.addClickListener(new ClickListener() {	
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						fieldGroup.commit();
						UserService.getInstance().login(user);
						setContent(new MainView());
					} catch (BadPassEx e) {
						new Notification("Złe hasło! ").show(Page.getCurrent());
						
						
					} catch (NoSuchUserEx e) {
						new Notification("Nie istnieje taki użytkownik").show(Page.getCurrent());

					} catch (CommitException e) {
						e.printStackTrace();
					}
				}
			});
			setCompositionRoot(formLayout);
		}

	}

	class RegisterWindow extends CustomComponent {

		public RegisterWindow() {

			VerticalLayout root = new VerticalLayout();
			PasswordField passTF = new PasswordField();
			passTF.setCaption("Hasło");
			passTF.addValidator(new StringLengthValidator(
					"Hasło musi zawierać co najmniej 5 znaków", 5,20,true));
			passTF.setRequired(true);
			TextField loginTF = new TextField();
			loginTF.setCaption("Login");
			loginTF.setRequired(true);
			loginTF.addValidator(new StringLengthValidator(
					"Nazwa użytkownika musi zawierać co najmniej 5 znaków", 5,20,true));
			TextField emailTF = new TextField();
			emailTF.setCaption("Email");
			emailTF.setRequired(true);
			emailTF.addValidator(new EmailValidator("Niewłaściwy adres email. "));
			root.addComponents(loginTF, passTF, emailTF);
			Button newAccountButton = new Button("Nowe Konto");
			root.addComponent(newAccountButton);
			
			newAccountButton.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					try{
						loginTF.validate(); 
						passTF.validate();
						emailTF.validate();
						UserService.getInstance().newAccout(new User(loginTF.getValue(), passTF.getValue(), emailTF.getValue()));
						loginTF.clear();
						passTF.clear();
						emailTF.clear();
						Notification.show("Konto zostało utworzone");
					} catch(InvalidValueException e){
						Notification.show(e.getMessage());
					}
				}
			});
			setCompositionRoot(root);
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
