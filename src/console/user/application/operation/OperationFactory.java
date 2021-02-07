package console.user.application.operation;

import console.user.application.util.ConsoleInputUtil;
import console.user.application.util.ParamValidator;
import console.user.application.model.Role;
import console.user.application.model.User;
import console.user.application.service.UserService;
import console.user.application.service.UserServiceImpl;

import java.io.BufferedReader;
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
import static console.user.application.util.ConsoleInputUtil.consoleInput;

public class OperationFactory {

    private final UserService userService = new UserServiceImpl();

    public List<Operation> getAllOperation() {
        return Arrays.asList(operationCreate(), operationGetUser(), operationGetAll(), operationUpdate(), operationDelete());
    }

    private Operation operationGetUser() {
        return new Operation(GET_USER_OPERATION_DESCRIPTION, x -> {
            System.out.println(GET_USER_OPERATION_DESCRIPTION);
            try {
                System.out.println(SET_USER_NAME_TEXT);
                String name = consoleInput();

                System.out.println(SET_USER_SECOND_NAME_TEXT);
                String secondName = consoleInput();

                User user = userService.get(name, secondName);
                if (user != null) {
                    System.out.println(user);
                } else {
                    System.out.println(USER_NOT_EXISTS);
                }
                System.out.println();
                System.out.println(OPERATION_SUCCESS);
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
            System.out.println();
            System.out.println(OPERATION_SUCCESS);
        });
    }

    private Operation operationCreate() {
        return new Operation(CREATE_OPERATION_DESCRIPTION, x -> {
            System.out.println(CREATE_OPERATION_DESCRIPTION);

            try {
                System.out.println(SET_USER_NAME_TEXT);
                String name = consoleInput();

                System.out.println(SET_USER_SECOND_NAME_TEXT);
                String secondName = consoleInput();

                System.out.println(SET_USER_EMAIL_TEXT);
                String email = consoleInput();
                while (!ParamValidator.isValidEmail(email)) {
                    System.out.println(INCORRECT_EMAIL);
                    email = consoleInput();
                }

                System.out.println(SET_USER_ROLES_TEXT);
                List<Role> roles = new ArrayList<>();
                String roleString = consoleInput();
                while (!ParamValidator.isValidRole(roles, roleString)) {
                    roleString = consoleInput();
                }

                System.out.println(SET_USER_PHONES_TEXT);
                List<String> phones = new ArrayList<>();
                String phone = consoleInput();
                if (ParamValidator.isValidPhone(phone)) {
                    phones.add(phone);
                } else {
                    System.out.println("Телефон должен быть в формате 375** *******");
                }

                userService.save(new User(name, secondName, email, roles, phones));

                System.out.println();
                System.out.println(OPERATION_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Operation operationUpdate() {
        return new Operation(UPDATE_USER_OPERATION_DESCRIPTION, x -> {
            BufferedReader reader = ConsoleInputUtil.getConsoleInput();
            System.out.println(UPDATE_USER_OPERATION_DESCRIPTION);

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Operation operationDelete() {
        return new Operation(DELETE_USER_OPERATION_DESCRIPTION, x -> {
            System.out.println(DELETE_USER_OPERATION_DESCRIPTION);
            try {
                System.out.println(SET_USER_NAME_TEXT);
                String name = consoleInput();

                System.out.println(SET_USER_SECOND_NAME_TEXT);
                String secondName = consoleInput();

                userService.delete(name, secondName);

                System.out.println(OPERATION_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
