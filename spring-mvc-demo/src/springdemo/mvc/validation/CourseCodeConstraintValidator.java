package springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String > {

	private String coursePrefix;
	@Override
	public void initialize(CourseCode theCourseCode) {
		// TODO Auto-generated method stub
		coursePrefix = theCourseCode.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		boolean res = false;
		if(theCode!=null) {
			 res = theCode.startsWith(coursePrefix);
		}
		else {
			res = true;
		}
		return res;
	}

	
}
