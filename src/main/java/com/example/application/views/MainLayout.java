package com.example.application.views;

import com.example.application.views.about.AboutView;
import com.example.application.views.helloworld.HelloWorldView;
import com.example.application.views.personform.PersonFormView;
import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

//@Push
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
//@Component
@PageTitle("Main")
public class MainLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    private DefaultBadgeHolder badge = new DefaultBadgeHolder(5);

    public MainLayout() {
        notifications.addClickListener(notification -> {/* ... */});

        LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), AboutView.class);
        badge.bind(menuEntry.getBadge());

        init(AppLayoutBuilder.get(LeftLayouts.LeftResponsive.class)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png"),
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ..."))
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), PersonFormView.class),
                                new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), AboutView.class),
                                LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftSubMenuBuilder
                                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                                        .add(new LeftNavigationItem("Charts", VaadinIcon.SPLINE_CHART.create(), AboutView.class),
                                                                new LeftNavigationItem("Contact", VaadinIcon.CONNECT.create(), HelloWorldView.class),
                                                                new LeftNavigationItem("More", VaadinIcon.COG.create(), PersonFormView.class))
                                                        .build(),
                                                new LeftNavigationItem("Contact1", VaadinIcon.CONNECT.create(), AboutView.class),
                                                new LeftNavigationItem("More1", VaadinIcon.COG.create(), AboutView.class))
                                        .build(),
                                menuEntry)
                        .addToSection(FOOTER, new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ...")))
                        .build())
                .build());
    }

    public DefaultNotificationHolder getNotifications() {
        return notifications;
    }

    public DefaultBadgeHolder getBadge() {
        return badge;
    }
}
