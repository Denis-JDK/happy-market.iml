import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    private List<Stage> stages;

    public Race (Stage [] stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public List <Stage> getStages() {
        return stages;
    }
}
