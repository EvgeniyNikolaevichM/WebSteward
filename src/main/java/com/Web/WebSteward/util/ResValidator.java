//package com.Web.WebSteward.util;
//
//import com.Web.WebSteward.models.Res;
//import com.Web.WebSteward.services.ResService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//
//@Component
//public class ResValidator implements Validator {
//
//    private final ResService resService;
//
//    @Autowired
//    public ResValidator(ResService resService) {
//        this.resService = resService;
//    }
//
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Res.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        Res res = (Res) o;
//
//        if (resService.getResTitle(res.getTitle()).isPresent())
//            errors.rejectValue("title", "", "Такой РЭС уже существует");
//    }
//}
