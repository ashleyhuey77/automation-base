package com.utils.devtools.filter;

import com.config.setup.report.LocalValidation;
import com.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptException;
import java.io.File;

@Slf4j
public class MessageFilter extends AbstractFilter {

    private String className;

    public MessageFilter(String name) {
        this.className = name;
    }

    @Override
    public void execute(JavascriptException exception) throws Exception {
        super.execute(exception);
        String name = exception.getMessage();
        Exclude exclude = new Exclude().get(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "exclude.json");
        if (!exclude.exceptions.contains(name)) {
            LocalValidation.getValidations().assertionFailed(log, "JS Errors on " + className + " => " + name);
        }
    }
}
