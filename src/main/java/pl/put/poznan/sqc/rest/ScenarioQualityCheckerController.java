package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.logic.Keyword;
import pl.put.poznan.sqc.logic.NoActor;
import pl.put.poznan.sqc.logic.StepsCount;
import pl.put.poznan.sqc.logic.Visitor;
import pl.put.poznan.sqc.scenario.Scenario;

/**
 * Rest controller class
 */
@RestController
@RequestMapping("/sqc")
public class ScenarioQualityCheckerController {

    /**
     *  logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * steps-count endpoint service
     *
     * @param json scenario received in request body
     * @return count of steps in scenario in json format
     */
    @GetMapping(path = "/steps-count", produces = "application/json")
    public StepsCount getStepsCount(@RequestBody Scenario json) {
        logger.debug("REST getStepsCount with body: "+ json);
        logger.info("Start steps-count function");
        StepsCount stepsCount = new StepsCount(json);
        stepsCount.accept(new Visitor(), json);
        return stepsCount;
    }

    /**
     * keyword endpoint service
     *
     * @param json scenario received in request body
     * @return number of steps in the scenario which begin with the keyword (json format)
     */
    @GetMapping(path = "/keyword", produces = "application/json")
    public Keyword getKeyword(@RequestBody Scenario json) {
        logger.debug("REST getKeyword with body: "+ json);
        logger.info("Start keyword function");
        Keyword keyword = new Keyword(json);
        keyword.accept(new Visitor(), json);
        return keyword;
    }

    /**
     * no-actor endpoint service
     *
     * @param json scenario received in request body
     * @return list of steps in the scenario which not begin with the actor name (json format)
     */
    @GetMapping(path = "/no-actor", produces = "application/json")
    public NoActor getNoActor(@RequestBody Scenario json) {
        logger.debug("REST getNoActor with body: "+ json);
        logger.info("Start noActor function");
        NoActor noActor = new NoActor(json);
        noActor.accept(new Visitor(), json);
        return noActor;
    }

//    /**
//     * send-scenario endpoint service
//     *
//     * @param json scenario received in request body
//     * @return numbered scenario (json format)
//     */
//    @GetMapping(path = "/send-scenario", produces = "application/json")
//    public SendScenario getSendScenario(@RequestBody Scenario json) {
//        logger.debug(json.toString());
//        return new SendScenario();
//    }

}