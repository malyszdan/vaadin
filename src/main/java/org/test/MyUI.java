package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
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

		setContent(new HorizontalLayout(new RegisterWindow(), new LoginWindow()));
	}

	class MainView extends CustomComponent {

		BeanItemContainer<Car> cars = new BeanItemContainer<>(Car.class);

		public MainView() {

			VerticalLayout root = new VerticalLayout();

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
			
			VerticalLayout formLayout = new VerticalLayout();
			
			FormLayout form = new FormLayout();
			Car newCar = new Car();
			FieldGroup fieldGroup = new FieldGroup(new BeanItem<Car>(newCar));
			Field<?> model= fieldGroup.buildAndBind("Model", "model");
			model.setRequired(true);
			model.addValidator(new StringLengthValidator("Model w zakresie od 1 do 20 znakow",
					1, 20, true));
			Field<?> distance = fieldGroup.buildAndBind("Przebieg", "distance");
			distance.setRequired(true);
			distance.addValidator(new DoubleRangeValidator("Chcesz wypożyczyć rakiete?", 0.0, 100000000000.0));
			Field<?> brand = fieldGroup.buildAndBind("Marka", "brand");
			brand.setRequired(true);
			Field<?> availability = fieldGroup.buildAndBind("Dostępność", "free");
			availability.setRequired(true);
			Field<?> yearField = fieldGroup.buildAndBind("Rok Produkcji", "yearProd");
			yearField.setRequired(true);
			yearField.addValidator(new IntegerRangeValidator("Czy to Wehikuł czasu? ", 1900, 2016));
			form.addComponents(model, distance, brand, availability, yearField);
			//form.addComponents(removeCarButton, changeStatusButton);
			
			Button addCarButton = new Button("Dodaj");
			addCarButton.addClickListener(event -> {
				
				try {
					fieldGroup.commit();
					CarService.getInstance().addCar(newCar.copy());
					cars.removeAllItems();
					cars.addAll(CarService.getInstance().getAllCars());
				} catch (Exception e) {
					// TODO Auto-generated catch block
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

		private User user = new User("-", "-");

		private FieldGroup fieldGroup = new FieldGroup(new BeanItem<User>(user));


		public LoginWindow() {

			FormLayout formLayout = new FormLayout();
			
			final Field<?> loginField = fieldGroup.buildAndBind("Login", "name");
			loginField.addValidator(new StringLengthValidator("Login musi być w przedziale 4-10",
					4, 10, true));		
			formLayout.addComponent(loginField);
			Button loginButton = new Button("Zaloguj");
			Field<?> passFIeld = fieldGroup.buildAndBind("Hasło","pass");
			passFIeld.addValidator(new StringLengthValidator("Login musi być w przedziale 4-10",
					4, 10, true));	
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
			passTF.setMaxLength(10);
			passTF.setCaption("Hasło");
			TextField loginTF = new TextField();
			loginTF.setCaption("Login");
			root.addComponent(loginTF);
			root.addComponent(passTF);
			Button newAccountButton = new Button("Nowe Konto");
			root.addComponent(newAccountButton);
			newAccountButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					UserService.getInstance().newAccout(new User(loginTF.getValue(), passTF.getValue()));
					loginTF.clear();
					passTF.clear();
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
