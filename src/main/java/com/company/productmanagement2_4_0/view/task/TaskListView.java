package com.company.productmanagement2_4_0.view.task;

import com.company.productmanagement2_4_0.entity.Task;
import com.company.productmanagement2_4_0.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "tasks", layout = MainView.class)
@ViewController(id = "Task_.list")
@ViewDescriptor(path = "task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
}