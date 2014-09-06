package jp.co.biglobe.lib.danger.validation.valueobjectnotempty;

class StringValidation implements Validation {

    private final String value;

    StringValidation(Object value) {
        this.value = (String) value;
    }

    @Override
    public boolean run() {
        return !(value == null || value.length() == 0);

    }
}
