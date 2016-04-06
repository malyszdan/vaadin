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
			//carsTable.addListener();
			
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
			
			
			FormLayout form = new FormLayout();
			Car newCar = new Car();
			FieldGroup fieldGroup = new FieldGroup(new BeanItem<Car>(newCar));
			form.addComponent(fieldGroup.buildAndBind("Model", "model"));
			form.addComponent(fieldGroup.buildAndBind("Przebieg", "distance"));
			form.addComponent(fieldGroup.buildAndBind("Marka", "brand"));
			form.addComponent(fieldGroup.buildAndBind("Dostępność", "free"));
			form.addComponent(removeCarButton);
			form.addComponent(changeStatusButton);
			Field<?> yearField = fieldGroup.buildAndBind("Rok Produkcji", "yearProd");
			yearField.setRequired(true);
			form.addComponent(yearField);
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
			root.addComponents(carsTable,form);
			setCompositionRoot(root);
		}

	}

	class LoginWindow extends CustomComponent {

		private User user = new User("-", "-");

		private FieldGroup fieldGroup = new FieldGroup(new BeanItem<User>(user));


		public LoginWindow() {

			FormLayout formLayout = new FormLayout();

			final Field<?> loginField = fieldGroup.buildAndBind("Login", "name");
			formLayout.addComponent(loginField);

			Button loginButton = new Button("Zaloguj");

			Field<?> passFIeld = fieldGroup.buildAndBind("Hasło", "pass");
			formLayout.addComponent(passFIeld);

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
			TextField passTF = new TextField();
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
