package console.user.application.util;

import console.user.application.model.Role;
import console.user.application.operation.Operation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static console.user.application.operation.OperationConstant.INCORRECT_ROLE;
import static console.user.application.operation.OperationConstant.SAME_LEVEL_ROLE;
import static console.user.application.operation.OperationConstant.MANY_ROLES;
import static console.user.application.operation.OperationConstant.MANY_PHONES;
import static console.user.application.operation.OperationConstant.INCORRECT_PHONE;
import static console.user.application.operation.OperationConstant.SUPER_ADMIN_ROLE;

public class ParamValidator {

    private ParamValidator() {}

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z0-9]+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^375[0-9]{2} [0-9]+$");
    private static final List<String> ACTIVE_ROLES = Arrays.asList("USER", "CUSTOMER", "ADMIN", "PROVIDER", "SUPER_ADMIN");

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        List<String> telephones = buildTelephoneListFromString(phone);
        if (telephones.size() > 3) {
            System.out.println(MANY_PHONES);
            return false;
        }
        for (String telephone : telephones) {
            if (!PHONE_PATTERN.matcher(telephone.trim()).matches()) {
                System.out.println(INCORRECT_PHONE);
                return false;
            }
        }
        return true;
    }

    public static boolean isValidRole(List<Role> roles, String candidate) {
        if (candidate == null || candidate.equals("")) {
            return false;
        }
        List<String> rolesList = buildListFromString(candidate.toUpperCase());
        if (rolesList.size() > 2) {
            System.out.println(MANY_ROLES);
            return false;
        }
        for (String r : rolesList) {
            if (ACTIVE_ROLES.contains(r)) {
                Role role = Role.valueOf(r);
                if (hasSameRoleLevel(roles, role)) {
                    System.out.println(SAME_LEVEL_ROLE);
                    roles.clear();
                    return false;
                } else if (hasSuperAdminRole(roles, role)) {
                    System.out.println(SUPER_ADMIN_ROLE);
                    return false;
                }
                else {
                    roles.add(role);
                }
            } else {
                System.out.println(INCORRECT_ROLE + r);
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrectPosition(String position, List<Operation> operations) {
        if (!isNumeric(position)) {
            return false;
        }
        int pos = Integer.parseInt(position) - 1;
        return pos >= 0 && pos < operations.size();
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(strNum).matches();
    }

    private static List<String> buildListFromString(String candidate) {
        if (candidate.contains("+")) {
            return Arrays.asList(candidate.split("\\+"));
        } else {
            return Collections.singletonList(candidate);
        }
    }

    private static boolean hasSameRoleLevel(List<Role> roles, Role candidate) {
        if (roles.contains(candidate)) {
            return true;
        }
        for (Role r : roles) {
            if (r.getLevel() == candidate.getLevel()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSuperAdminRole(List<Role> roles, Role role) {
        if (roles.contains(Role.SUPER_ADMIN)) {
            return true;
        }
        return !roles.isEmpty() && role == Role.SUPER_ADMIN;
    }

    private static List<String> buildTelephoneListFromString(String telephone) {
        if (telephone.contains(",")) {
            return Arrays.asList(telephone.split(","));
        } else {
            return Collections.singletonList(telephone);
        }
    }
}
