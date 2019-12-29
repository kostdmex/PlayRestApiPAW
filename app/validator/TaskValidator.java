package validator;

import json.task.TaskJsonPost;
import json.task.TaskListJsonPost;

import javax.inject.Singleton;

@Singleton
public class TaskValidator {

    public static boolean validateTaskListJsonPost(TaskListJsonPost taskListJsonPost){
        return taskListJsonPost.getCardId() != null && taskListJsonPost.getName() != null;
    }

    public static boolean validateTaskJsonPost(TaskJsonPost taskJsonPost){
        return taskJsonPost.getName() != null && taskJsonPost.getTaskListId() != null;
    }

}
