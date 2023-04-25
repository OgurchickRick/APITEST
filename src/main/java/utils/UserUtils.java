package utils;

import model.User;
import org.testng.Assert;

public class UserUtils {

    public static void assertUserEquals(User actualUser, User expectedUser) {
        Assert.assertEquals(actualUser.getName(), expectedUser.getName());
        Assert.assertEquals(actualUser.getJob(), expectedUser.getJob());
    }
}
