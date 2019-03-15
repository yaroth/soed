package persistance.textfile;

import task.Task;

import java.io.IOException;
import java.util.List;

/**
 * Created by yann on 26.02.18.
 */
public interface TaskListDAO {

    void save(List<Task> taskList) throws IOException;
}
