package jp.co.biglobe.isp.mobile.extension.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FuriganaValidator implements ConstraintValidator<Furigana, String> {

    @Override
    public void initialize(Furigana required) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        if(value == null || value.equals("")){
            return true;
        }

        return checkHankakuKana(value);

    }

    private boolean checkHankakuKana(String value) {
        return value.matches("^[a-zA-Z0-9 ｰ｡｢｣､･ｧｨｩｪｫｬｭｮｯﾀｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾐﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟ]+$");
    }

}