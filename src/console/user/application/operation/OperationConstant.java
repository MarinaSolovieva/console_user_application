package console.user.application.operation;

public class OperationConstant {

    public static final String CREATE_OPERATION_DESCRIPTION = "Создание нового пользователя";
    public static final String GET_USER_OPERATION_DESCRIPTION = "Получить информацию о конекретном пользователе";
    public static final String GET_ALL_OPERATION_DESCRIPTION = "Получить информацию о всех пользователях";
    public static final String UPDATE_USER_OPERATION_DESCRIPTION = "Обновить информацию конкретного пользователя";
    public static final String DELETE_USER_OPERATION_DESCRIPTION = "Удалить пользователя";
    public static final String OPERATION_SUCCESS = "Действие прошло успешно! Вы можете выполнить новое действие указав " +
            "соотвествующее число или завершить работу приложения:";
    public static final String SET_USER_NAME_TEXT = "Введите имя пользователя:";
    public static final String SET_USER_SECOND_NAME_TEXT = "Введите фамилию пользователя:";
    public static final String SET_USER_EMAIL_TEXT = "Введите email пользователя:";
    public static final String SET_USER_ROLES_TEXT = "Введите роль пользователя (USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN или USER+ADMIN):";
    public static final String SET_USER_PHONES_TEXT = "Введите телефон пользователя (максиму 3 номера):";
    public static final String USER_NOT_EXISTS = "Пользователя с такими именем и фамилией не существует!";
    public static final String USERS_NOT_EXISTS = "В данный момент нет сохраненных пользователей!";
    public static final String INCORRECT_EMAIL = "Введен некорректные email.";
    public static final String INCORRECT_ROLE = "Введены некорректная роль";
    public static final String SAME_LEVEL_ROLE = "Введены несколько ролей с одним уровнем.";
    public static final String MANY_ROLES = "У пользователя не может быть больше 2-ух ролей.";

}
