package console.user.application.operation;

import console.user.application.util.ParamValidator;
import console.user.application.model.Role;
import console.user.application.model.User;
import console.user.application.service.UserService;
import console.user.application.service.UserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static console.user.application.operation.OperationConstant.SET_USER_NAME_TEXT;
import static console.user.application.operation.OperationConstant.SET_USER_SECOND_NAME_TEXT;
import static console.user.application.operation.OperationConstant.USER_NOT_EXISTS;
import static console.user.application.operation.OperationConstant.GET_USER_OPERATION_DESCRIPTION;
import static console.user.application.operation.OperationConstant.OPERATION_SUCCESS;
import static console.user.application.operation.OperationConstant.GET_ALL_OPERATION_DESCRIPTION;
import static console.user.application.operation.OperationConstant.USERS_NOT_EXISTS;
import static console.user.application.operation.OperationConstant.CREATE_OPERATION_DESCRIPTION;
import static console.user.application.operation.OperationConstant.UPDATE_USER_OPERATION_DESCRIPTION;
import static console.user.application.operation.OperationConstant.DELETE_USER_OPERATION_DESCRIPTION;
import static console.user.application.operation.OperationConstant.SET_USER_EMAIL_TEXT;
import static console.user.application.operation.OperationConstant.INCORRECT_EMAIL;
import static console.user.application.operation.OperationConstant.SET_USER_ROLES_TEXT;
import static console.user.application.operation.OperationConstant.SET_USER_PHONES_TEXT;
import static console.user.application.operation.OperationConstant.EXIT_TO_MAIN_MENU;
import static console.user.application.operation.OperationConstant.MAIN_MENU_TEXT;
import static console.user.application.operation.OperationConstant.TO_MAIN_MENU;
import static console.user.application.util.ConsoleInputUtil.allOperationConsoleOutput;
import static console.user.application.util.ConsoleInputUtil.consoleInput;

public class OperationFactory {

    private final UserService userService = new UserServiceImpl();
    private final List<Operation> allOperation = this.getAllOperation();

    public List<Operation> getAllOperation() {
        return Arrays.asList(operationCreate(), operationGetUser(), operationGetAll(), operationUpdate(), operationDelete());
    }

    private Operation operationGetUser() {
        return new Operation(GET_USER_OPERATION_DESCRIPTION, x -> {
            System.out.println(GET_USER_OPERATION_DESCRIPTION);
            try {
                String name = getFromConsoleWithText(SET_USER_NAME_TEXT);
                String secondName = getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT);

                User user;
                while ((user = userService.get(name, secondName)) == null) {
                    System.out.println(USER_NOT_EXISTS);
                    System.out.println(SET_USER_NAME_TEXT + EXIT_TO_MAIN_MENU);
                    name = consoleInput();
                    if ("menu".equalsIgnoreCase(name)) {
                        System.out.println(MAIN_MENU_TEXT);
                        return;
                    }
                    System.out.println(SET_USER_SECOND_NAME_TEXT);
                    secondName = consoleInput();
                }
                System.out.println(user);
                successOperationConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Operation operationGetAll() {
        return new Operation(GET_ALL_OPERATION_DESCRIPTION, x -> {
            System.out.println(GET_ALL_OPERATION_DESCRIPTION);
            List<User> users = userService.getAll();
            if (users.isEmpty()) {
                System.out.println(USERS_NOT_EXISTS);
            } else {
                System.out.println(users);
            }
            successOperationConsoleInput();
        });
    }

    private Operation operationCreate() {
        return new Operation(CREATE_OPERATION_DESCRIPTION, x -> {
            System.out.println(CREATE_OPERATION_DESCRIPTION);
            try {
                doSave(getFromConsoleWithText(SET_USER_NAME_TEXT),
                        getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Operation operationUpdate() {
        return new Operation(UPDATE_USER_OPERATION_DESCRIPTION, x -> {
            System.out.println(UPDATE_USER_OPERATION_DESCRIPTION);
            try {
                String name = getFromConsoleWithText(SET_USER_NAME_TEXT);
                String secondName = getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT);

                while (userService.get(name, secondName) == null) {
                    System.out.println(USER_NOT_EXISTS);
                    System.out.println(SET_USER_NAME_TEXT + EXIT_TO_MAIN_MENU);
                    name = consoleInput();
                    if (TO_MAIN_MENU.equalsIgnoreCase(name)) {
                        System.out.println(MAIN_MENU_TEXT);
                        return;
                    }
                    secondName = getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT);
                }
                doSave(name, secondName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Operation operationDelete() {
        return new Operation(DELETE_USER_OPERATION_DESCRIPTION, x -> {
            System.out.println(DELETE_USER_OPERATION_DESCRIPTION);
            try {
                String name = getFromConsoleWithText(SET_USER_NAME_TEXT);
                String secondName = getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT);

                while (!userService.delete(name, secondName)) {
                    System.out.println(USER_NOT_EXISTS);
                    System.out.println(SET_USER_NAME_TEXT + EXIT_TO_MAIN_MENU);
                    name = consoleInput();
                    if (TO_MAIN_MENU.equalsIgnoreCase(name)) {
                        System.out.println(MAIN_MENU_TEXT);
                        return;
                    }
                    secondName = getFromConsoleWithText(SET_USER_SECOND_NAME_TEXT);
                }
                successOperationConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void doSave(String name, String secondName) throws IOException {
        String email = getEmailFromConsole();

        System.out.println(SET_USER_ROLES_TEXT);
        List<Role> roles = new ArrayList<>();
        String roleString = consoleInput();
        while (!ParamValidator.isValidRole(roles, roleString)) {
            roleString = consoleInput();
        }

        System.out.println(SET_USER_PHONES_TEXT);
        List<String> phones = new ArrayList<>();
        String phone = consoleInput();
        while (!ParamValidator.isValidPhone(phone)) {
            phone = consoleInput();
        }
        if (ParamValidator.isValidPhone(phone)) {
            phones.add(phone);
        }
        userService.save(new User(name, secondName, email, roles, phones));
        successOperationConsoleInput();
    }

    private String getEmailFromConsole() throws IOException {
        System.out.println(SET_USER_EMAIL_TEXT);
        String email = consoleInput();
        while (!ParamValidator.isValidEmail(email)) {
            System.out.println(INCORRECT_EMAIL);
            email = consoleInput();
        }
        return email;
    }

    private String getFromConsoleWithText(String output) throws IOException {
        System.out.println(output);
        return consoleInput();
    }

    private void successOperationConsoleInput() {
        System.out.println();
        System.out.println(OPERATION_SUCCESS);
        allOperationConsoleOutput(allOperation);
    }
}
