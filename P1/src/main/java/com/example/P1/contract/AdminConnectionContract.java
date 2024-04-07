package com.example.P1.contract;

import com.example.P1.model.Admin;
import com.example.P1.model.Content;

import java.util.List;

/**
 * a contract interface to make sure the methods
 * to make CRUD admin operations are implemented
 */
public interface AdminConnectionContract {
    public Admin getAdminById(String id);

    public List<Admin> getAllAdmins();

    public Admin addAdmin(Admin admin);

    public void deleteAdminById(String id);

    public Admin updateAdmin(Admin admin);

    /**
     *
     * @param adminId  id of the admin that added the content in the platform
     * @return all the content added by the admin
     */
    public List<Content> getAllContentByAdminId(String adminId);
}
