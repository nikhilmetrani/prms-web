package sg.edu.nus.iss.phoenix.authenticate.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User Value Object. This class is value object representing database table
 * user This class is intented to be used together with associated Dao object.
 */
public class User implements Cloneable, Serializable {

    /**
     * For eclipse based unique identity
     */
    private static final long serialVersionUID = -3737184031423373198L;
    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    private String id;
    private String password;
    private String name;
    private ArrayList<Role> roles = new ArrayList<Role>();
    private String role;
    private boolean activeUserFlag;
    private String email;
    private String phoneNumber;
    private Profile profile;

    /**
     * Constructors. The first one takes no arguments and provides the most
     * simple way to create object instance. The another one takes one argument,
     * which is the primary key of the corresponding table.
     */

    public User() {
        id = "";
        password = "";
        name = "";
        //role = "";
        activeUserFlag = true;
        email = "";
        phoneNumber = "";
        profile = new Profile();
    }

    public User(String idIn) {
        id = idIn;
    }

    /**
     * Get- and Set-methods for persistent variables. The default behaviour does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     */
    public String getId() {
        return id;
    }

    public void setId(String idIn) {
        if (null != idIn)
            id = idIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordIn) {
        password = passwordIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameIn) {
        name = nameIn;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public boolean isActiveUserFlag() {
        return activeUserFlag;
    }

    public void setActiveUserFlag(boolean activeUserFlag) {
        this.activeUserFlag = activeUserFlag;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        if(null != profile)
            this.profile = profile.clone();
    }
    
    public boolean hasRole(String role) {
        return getRoleString().contains(role);
    }
    
    /**
     * setAll allows to set all persistent variables in one method call. This is
     * useful, when all data is available and it is needed to set the initial
     * state of this object. Note that this method will directly modify instance
     * variales, without going trough the individual set-methods.
     */
    public void setAll(String idIn, String passwordIn, String nameIn,
            String roleIn) {
        id = idIn;
        password = passwordIn;
        name = nameIn;
        Role e = new Role(roleIn);
        roles.add(e);
    }

    /**
     * hasEqualMapping-method will compare two User instances and return true if
     * they contain same values in all persistent instance variables. If
     * hasEqualMapping returns true, it does not mean the objects are the same
     * instance. However it does mean that in that moment, they are mapped to
     * the same row in database.
     */
    public boolean hasEqualMapping(User valueObject) {

        if (valueObject.getId() != id) {
            return (false);
        }
        if (password == null) {
            if (valueObject.getPassword() != null) {
                return (false);
            }
        } else if (!password.equals(valueObject.getPassword())) {
            return (false);
        }
        if (name == null) {
            if (valueObject.getName() != null) {
                return (false);
            }
        } else if (!name.equals(valueObject.getName())) {
            return (false);
        }
        if (roles.get(0).getRole() != null) {
            if (valueObject.roles.get(0).getRole() != null) {
                return (false);
            }
        } else if (!roles.get(0).equals(valueObject.roles.get(0).getRole())) {
            return (false);
        }

        return true;
    }

    /**
     * toString will return String object representing the state of this
     * valueObject. This is useful during application development, and possibly
     * when application is writing object states in console logs.
     */
    public String toString() {
        StringBuffer out = new StringBuffer("toString: ");
        out.append("\nclass User, mapping to table user\n");
        out.append("Persistent attributes: \n");
        out.append("id = " + id + "\n");
        out.append("password = " + password + "\n");
        out.append("name = " + name + "\n");
        out.append("role = " + getRoleString() + "\n");
        return out.toString();
    }

    /**
     * Clone will return identical deep copy of this valueObject. Note, that
     * this method is different than the clone() which is defined in
     * java.lang.Object. Here, the returned cloned object will also have all its
     * attributes cloned.
     */
    public Object clone() {
        User cloned = new User();
        if (id != null) {
            cloned.setId(new String(id));
        }
        if (password != null) {
            cloned.setPassword(new String(password));
        }
        if (name != null) {
            cloned.setName(new String(name));
        }
        if (roles != null && !roles.isEmpty()) {
            cloned.setRoles(roles);
        }
        if (email != null) {
            cloned.setEmail(new String(email));
        }
        if (phoneNumber != null) {
            cloned.setPhoneNumber(phoneNumber);
        }
        if (profile != null) {
            cloned.setProfile(profile);
        }
        return cloned;
    }

    public String getRoleString() {
        String roleString = "";
        ArrayList<Role> roles = getRoles();
        for (int i = 0; i < roles.size(); i++) {
            roleString += roles.get(i).getRole();
            if (i < roles.size() - 1) {
                roleString += ":";
            }
        }
        return roleString;
    }

}
