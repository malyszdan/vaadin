package war;

import java.awt.TextArea;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("war.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        TextField sample = new TextField();
        sample.setImmediate(true);
        sample.setInputPrompt("Write something");
        sample.setMaxLength(10);
        TextField.updateCaption(0);
        
        sample.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(final TextChangeEvent event) {
                updateCaption(event.getText().length());
            }
        });

        private void updateCaption(final int textLength) {
            final StringBuilder builder = new StringBuilder();
            builder.append(String.valueOf(textLength));
            if (sample.getMaxLength() >= 0) {
                builder.append("/").append(sample.getMaxLength());
            }
            builder.append(" characters");
            sample.setCaption(builder.toString());
        }
     
     
            sample.addValueChangeListener(e -> Notification.show("Value changed:",
                    String.valueOf(e.getProperty().getValue()),
                    Type.TRAY_NOTIFICATION));
        
      
        
        Button oneBT = new Button("Wyślij");
        oneBT.addClickListener(new Button.ClickListener() {
        	@Override
        	public void buttonClick(ClickEvent event) {
        		Notification.show("Wysłano", Type.TRAY_NOTIFICATION);
        	}
        });
        
        //layout.addComponent(textFld);
        layout.addComponent(oneBT);
        

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
