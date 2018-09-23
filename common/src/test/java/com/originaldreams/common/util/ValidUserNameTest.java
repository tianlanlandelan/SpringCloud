package com.originaldreams.common.util;


import org.junit.Assert;
import org.junit.Test;

public class ValidUserNameTest {

    @Test
    public void validEmailAddressTest() {
        String email1 = "example@gmail.com";
        String email2 = "example-example@gmail.com";
        String email3 = "example+example@gmail.com";
        String email4 = "example_example@gmail.com";

        String emailError1 = "asdadwwddw";
        Assert.assertTrue(ValidUserName.isValidEmailAddress(email1));
        Assert.assertTrue(ValidUserName.isValidEmailAddress(email2));
        Assert.assertTrue(ValidUserName.isValidEmailAddress(email3));
        Assert.assertTrue(ValidUserName.isValidEmailAddress(email4));

        Assert.assertFalse(ValidUserName.isValidEmailAddress(emailError1));

    }

    @Test
    public void validUserNameTest() {

        String userName1 = "wqdqwfw";
        String userName2 = "wqdqwfwdsadasdas";
        String userName3 = "wqdqwfwdsad_asdas";
        String userNameError1 = "wqdqwfw@gmail.com";
        String userNameError2 = "wqdq@wfw";
        String userNameError3 = "wqdq.wfw";


        Assert.assertTrue(ValidUserName.isValidUserName(userName1));
        Assert.assertTrue(ValidUserName.isValidUserName(userName2));
        Assert.assertTrue(ValidUserName.isValidUserName(userName3));
        Assert.assertFalse(ValidUserName.isValidUserName(userNameError1));
        Assert.assertFalse(ValidUserName.isValidUserName(userNameError2));
        Assert.assertFalse(ValidUserName.isValidUserName(userNameError3));

    }

    @Test
    public void validPhoneNumberTest() {
        String phone1 = "18619775139";
        String phoneError1 = "1231232";
        String phoneError2 = "awdwddw12312321";
        String phoneError3 = "12_2131232";

        Assert.assertTrue(ValidUserName.isValidPhoneNumber(phone1));
        Assert.assertFalse(ValidUserName.isValidPhoneNumber(phoneError1));
        Assert.assertFalse(ValidUserName.isValidPhoneNumber(phoneError2));
        Assert.assertFalse(ValidUserName.isValidPhoneNumber(phoneError3));

    }
}
