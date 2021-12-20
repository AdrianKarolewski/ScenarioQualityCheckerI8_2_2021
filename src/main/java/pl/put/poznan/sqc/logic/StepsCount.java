package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

/**
 * StepsCount class implementation
 */
public class StepsCount implements ScenarioAbstractClass {

    private int numberofsteps = 0;

    /**
     * StepsCount class constructor
     *
     * @param scenario in Scenario type
     */
    public StepsCount(Scenario scenario){
        calculate(scenario);
    }

    /**
     * A method checking and counting substeps of each scenario step (and substep - recursively)
     *
     * @param step in Step type
     */
    private void checkSubsteps(Step step){
        if (step.getSubSteps().size() != 0) {
            numberofsteps += step.getSubSteps().size();
            for (Step subs : step.getSubSteps()){
                checkSubsteps(subs);
            }
        }
    }

    /**
     * A method calculating number of steps in a scenario
     *
     * @param scenario in Scenario type
     */
    @Override
    public void calculate(Scenario scenario){
        if (scenario.getSteps().size() == 0){
            numberofsteps = 0;
        }
        else {
            numberofsteps += scenario.getSteps().size();
            for (Step s : scenario.getSteps()){
                checkSubsteps(s);
            }
        }
    }
}