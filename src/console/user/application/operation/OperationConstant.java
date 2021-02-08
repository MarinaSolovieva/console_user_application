package console.user.application.operation;

public class OperationConstant {

    private OperationConstant() {}

    public static final String CREATE_OPERATION_DESCRIPTION = "Создание нового пользователя";
    public static final String GET_USER_OPERATION_DESCRIPTION = "Получить информацию о конкретном пользователе";
    public static final String GET_ALL_OPERATION_DESCRIPTION = "Получить информацию о всех пользователях";
    public static final String UPDATE_USER_OPERATION_DESCRIPTION = "Обновить информацию конкретного пользователя";
    public static final String DELETE_USER_OPERATION_DESCRIPTION = "Удалить пользователя";
    public static final String OPERATION_SUCCESS = "Действие прошло успешно! Вы можете выполнить новое действие указав " +
            "соотвествующее число или завершить работу приложения:";
    public static final String SET_USER_NAME_TEXT = "Введите имя пользователя";
    public static final String SET_USER_SECOND_NAME_TEXT = "Введите фамилию пользователя:";
    public static final String SET_USER_EMAIL_TEXT = "Введите email пользователя в формате: ****@***.***";
    public static final String SET_USER_ROLES_TEXT = "Введите роль пользователя (USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN или USER+ADMIN):";
    public static final String SET_USER_PHONES_TEXT = "Введите телефон пользователя в формате 375** *******, " +
            "максимальное число номеров: 3, разделенных ','";
    public static final String USER_NOT_EXISTS = "Пользователя с такими именем и фамилией не существует!";
    public static final String USERS_NOT_EXISTS = "В данный момент нет сохраненных пользователей!";
    public static final String INCORRECT_EMAIL = "Введен некорректный формат email.";
    public static final String INCORRECT_ROLE = "Введена некорректная роль ";
    public static final String INCORRECT_PHONE = "Введен некорректный телефон. Телефон должен быть в формате 375** *******";
    public static final String SAME_LEVEL_ROLE = "Введены несколько ролей с одним уровнем.";
    public static final String MANY_ROLES = "У пользователя не может быть больше 2-ух ролей.";
    public static final String SUPER_ADMIN_ROLE = "У пользователя с ролью SUPER_ADMIN может быть только эта роль";
    public static final String MANY_PHONES = "У пользователя не может быть больше 3-х телефонов.";
    public static final String EXIT_TO_MAIN_MENU = " или для выхода в главное меню напишите 'menu'";
    public static final String MAIN_MENU_TEXT = "Вы вышли в главное меню, выберите цифру для дальнейшего действия ";
    public static final String TO_MAIN_MENU = "menu";
}
