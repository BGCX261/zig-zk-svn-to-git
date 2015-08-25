package com.twinstorm.zkskeleton.ui.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

public class LoginWindow extends Window {

	private static final Log log = LogFactory.getLog(LoginWindow.class);

	public void doLogin(String username, String password) {
		Form form = new Form();
		form.setDynamicProperty("action", "j_acegi_security_check");
		form.setDynamicProperty("method", "post");
		form.setPage(this.getPage());

		createInput(form, "hidden", "j_username", username);
		createInput(form, "hidden", "j_password", password);

		Clients.submitForm(form);
	}

	private void createInput(Form form, String type, String name, String value) {
		Input input = new Input();
		input.setParent(form);
		input.setDynamicProperty("type", type);
		input.setDynamicProperty("name", name);
		input.setValue(value);
	}

}
