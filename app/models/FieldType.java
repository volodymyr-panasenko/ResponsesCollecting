package models;

/**
 * Description of field type.
 */
public enum FieldType {
	SINGLE_LINE_TEXT, MULTI_LINE_TEXT, RADIO_BUTTON,
	CHECK_BOX, COMBO_BOX, DATE, SLIDER;

	@Override
	public String toString() {
		return name().substring(0, 1)
				.concat(name().substring(1).toLowerCase().replaceAll("_", " "));
	}

    public static FieldType toEnum(String type) {
        type = type.replaceAll(" ", "_").toUpperCase();
        return FieldType.valueOf(type);
    }

}
