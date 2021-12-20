package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

/**
 * Keyword counting class implementation
 */
public class Keyword implements ScenarioAbstractClass {

    private int numberofstepswithkeywords = 0;

    /**
     * Keyword class constructor
     *
     * @param scenario in Scenario type
     */
    public Keyword(Scenario scenario){ calculate(scenario); }

    /**
     * A method checking and counting keywords in substeps od each scenario step (and substep - recursively)
     *
     * @param step in Step type
     */
    private void checkSubsteps(Step step){
        if (step.getSubSteps().size() != 0) {
            for (Step subs : step.getSubSteps()){
                String content[] = subs.getContent().split(" ");
                if (content[0].equals("IF") || content[0].equals("ELSE") || (content[0].equals("FOR") && content[1].equals("EACH"))) {
                    numberofstepswithkeywords += 1;
                }
                checkSubsteps(subs);
            }
        }
    }

    /**
     * A method calculating number of steps with keywords IF, ELSE and FOR EACH in a scenario
     *
     * @param scenario in Scenario type
     */
    @Override
    public void calculate(Scenario scenario){
        if (scenario.getSteps().size() == 0) {
            numberofstepswithkeywords = 0;
        }
        else {
            for (Step s : scenario.getSteps()) {
                String content[] = s.getContent().split(" ");
                if (content[0].equals("IF") || content[0].equals("ELSE") || (content[0].equals("FOR") && content[1].equals("EACH"))) {
                    numberofstepswithkeywords += 1;
                }
                checkSubsteps(s);
            }
        }
    }
}
