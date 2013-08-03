package ch.genidea.geniweb.base.web.form;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ScriptAssert(
        lang = "javascript",
        script = "_this.confirmPassword.equals(_this.password)",
        message = "The passwords don't match"
)

public class UserForm {

    @NotNull
    @Size(min=1, max=50)
    private String screenName;

    @NotNull
    @Size(min=5, max=20)
    private String password;

    @NotNull
    @Size(min=5, max=20)
    private String confirmPassword;

    @NotNull
    @Size(min=5, max=50)
    @Email
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
