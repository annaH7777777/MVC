package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    private List<User> getAllUsers(){return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));}
    //(userService.getUsersBetweenLevels(1, 100)

    public void loadUsers(){
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());

    }
    public ModelData getModelData() {
        return modelData;
    }

    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(userService.getAllDeletedUsers());
    }
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
    public void deleteUserById(long id){
        userService.deleteUser(id);
        loadUsers();
    }
    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name, id, level);
        loadUsers();
    }
}
