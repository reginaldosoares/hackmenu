package com.example.application.views.crud;

import com.example.application.data.entity.Group;
import com.example.application.data.entity.User;
import com.example.application.data.service.GroupService;
import com.example.application.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import javax.annotation.security.PermitAll;

@PageTitle("Crud")
@Route(value = "crud-case", layout = MainLayout.class)
@PermitAll
public class CrudView extends VerticalLayout {

    public CrudView(UserService userService, GroupService groupService) {
        // crud instance
        GridCrud<User> crud = new GridCrud<>(User.class);

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        // grid configuration
        crud.getGrid().setColumns("name", "birthDate", "maritalStatus", "email", "phoneNumber", "active");
        crud.getGrid().setColumnReorderingAllowed(true);

        // form configuration
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "birthDate", "email", "salary", "phoneNumber", "maritalStatus", "groups", "active", "mainGroup",
                "password");
        crud.getCrudFormFactory().setVisibleProperties(
                "name", "birthDate", "email", "salary", "phoneNumber", "maritalStatus", "groups", "active", "mainGroup");
        crud.getCrudFormFactory().setFieldProvider("mainGroup",
                new ComboBoxProvider<>(groupService.findAll()));
        crud.getCrudFormFactory().setFieldProvider("groups",
                new CheckBoxGroupProvider<>(groupService.findAll()));
        crud.getCrudFormFactory().setFieldProvider("groups",
                new CheckBoxGroupProvider<>("Groups", groupService.findAll(), Group::getName));
        crud.getCrudFormFactory().setFieldProvider("mainGroup",
                new ComboBoxProvider<>("Main Group", groupService.findAll(), new TextRenderer<>(Group::getName), Group::getName));

        // layout configuration
        setSizeFull();
        add(crud);

        // logic configuration
        crud.setOperations(
                () -> userService.findByNameContainingIgnoreCase(filter.getValue()),
                user -> userService.save(user),
                user -> userService.save(user),
                user -> userService.delete(user)
        );

        filter.addValueChangeListener(e -> crud.refreshGrid());
    }

}
