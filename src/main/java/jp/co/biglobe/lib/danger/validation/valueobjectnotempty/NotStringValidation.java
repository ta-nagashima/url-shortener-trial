package jp.co.biglobe.lib.danger.validation.valueobjectnotempty;

class NotStringValidation implements Validation {

    private  final Object value;

    NotStringValidation(Object value) {
        this.value = value;
    }

    @Override
    public boolean run() {

        return value != null;

    }
}
