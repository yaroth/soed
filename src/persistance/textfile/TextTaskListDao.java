package persistance.textfile;

import task.Task;

import java.io.*;
import java.util.List;

/**
 * Created by yann on 01.03.18.
 */
public class TextTaskListDao implements TaskListDAO {

    private File file;

    public TextTaskListDao(File file) {
        this.file = file;
    }


    @Override
    public void save(List<Task> taskList) throws IOException {
        try (
                PrintWriter printWriter = new PrintWriter(file);
        ) {
            for (Task task : taskList) {
                printWriter.println(task);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
}
