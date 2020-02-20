
import org.apache.jena.query.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Akinator {
    final boolean printQuery = true;
    final boolean printResults = true;
    final boolean printQuestions = true;

    public List<Question> listOfQuestion = new ArrayList<Question>();


    private String initialQuery_String_aux = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + "select distinct ?s \r\n" + "where { ?s a <http://dbpedia.org/class/yago/WikicatMemberStatesOfTheUnitedNations> .\r\n";
    private int number_of_Countries = 188;
    private int country_found;
    private int number_of_questions_left;
    private boolean useless_question;


    public void initializare() {

Question language_english = new Question("Does your country have as official language English?",
        "?s dbo:officialLanguage ?language1.\n",
        "filter (str(?language1)=\"http://dbpedia.org/resource/English_language\")\n",
        "filter not exists {\n" + "?s dbo:officialLanguage ?language1.\n" + "filter (str(?language1)=\"http://dbpedia.org/resource/English_language1\")}");

Question language_french = new Question("Does your country have as official language French?",
        "?s dbo:officialLanguage ?language2.\n",
        "filter (str(?language2)=\"http://dbpedia.org/resource/French_language\")\n",
        "filter not exists {\n" + "?s dbo:officialLanguage ?language2.\n" + "filter (str(?language2)=\"http://dbpedia.org/resource/French_language\")}");


Question aria = new Question("Does your country have an aria bigger than 238 000 hm^2 (~=Romania)",
        "?s dbo:areaTotal ?aria.\n",
        "FILTER(?aria >238000000000)\n",
        "filter not exists{?s dbo:areaTotal ?aria.\n" + "FILTER(?aria > 238390000000)}");

Question currency_Euro = new Question("Does your country use Euros?",
        "?s dbo:currency ?currency1.\n",
        "filter contains(str(?currency1),\"Euro\")\n",
        "filter not exists{?s dbo:currency ?currency1.\n" + "filter contains(str(?currency1),\"Euro\")}");
Question currency_Dollar = new Question("Does your country use Dollars?",
        "?s dbo:currency ?currency.\n",
        "filter (str(?currency)=\"http://dbpedia.org/resource/United_States_dollar\")\n",
        "filter not exists{?s dbo:currency ?currency.\n" + " filter (str(?currency)=\"http://dbpedia.org/resource/United_States_dollar\" )}");
Question time_zone_Central_European = new Question("Does you country belong to Central European Timezone",
        " ?s  dbo:timeZone ?timp.",
        "filter (str(?timp)=\"http://dbpedia.org/resource/Central_European_Time\")\n",
        "filter not exists { ?s  dbo:timeZone ?timp.\n" + "filter (str(?timp)=\"http://dbpedia.org/resource/Central_European_Time\")}");
Question time_zone_Eastern_European = new Question("Does you country belong to Eastern European Timezone",
        " ?s  dbo:timeZone ?timp1.",
        "filter (str(?timp1)=\"http://dbpedia.org/resource/Eastern_European_Time\")\n",
        "filter not exists { ?s  dbo:timeZone ?timp1.\n" + "filter (str(?timp1)=\"http://dbpedia.org/resource/Eastern_European_Time\")}");

Question unitary_state = new Question("Is your country a Unitary State?",
        "?s dbo:governmentType ?curr1.",
        "filter(str(?curr1)=\"http://dbpedia.org/resource/Unitary_state\")\n",
        "filter not exists { ?s dbo:governmentType ?curr1.\n" + "filter(str(?curr1)=\"http://dbpedia.org/resource/Unitary_state\")}");
Question parlamentary_state = new Question("Is your country a parliamentary state?",
        "?s dbo:governmentType ?curr2.",
        "filter(str(?curr2)=\"http://dbpedia.org/resource/Parliamentary_system\")\n",
        "filter not exists { ?s dbo:governmentType ?curr2.\n" + "filter(str(?curr2)=\"http://dbpedia.org/resource/Parliamentary_system\")}");
Question republic_state = new Question("Is your country a Republic State?",
        "?s dbo:governmentType ?curr3.",
        "filter(str(?curr3)=\"http://dbpedia.org/resource/Republic\")\n",
        "filter not exists { ?s dbo:governmentType ?curr3.\n" + "filter(str(?curr3)=\"http://dbpedia.org/resource/Republic\")}");
Question motto = new Question("Does your country have a motto",
        "?s  dbo:motto ?mottoo." + "\n",
        " ",
        " filter not exists { ?s dbo:motto ?mottoo}");

Question foundation_year = new Question("Does your country has been founded after 1900",
        "?s dbo:foundingDate ?year." + "\n",
        "filter (str(?year) >\"1900\")",
        "filter not exists {?s dbo:foundingDate ?year.\n" + "filter(str(?year) >\"1900\")}");

Question population_density = new Question("Does your country have a population density bigger than 50 people/km^2",
        "?s dbo:populationDensity ?pop" + "\n",
        "filter (?pop > 80)",
        "filter not exists{?s dbo:populationDensity ?pop.\n" + "      filter(?pop>80)     }");

        listOfQuestion.add(language_english);
        listOfQuestion.add(language_french);
        listOfQuestion.add(aria);
        listOfQuestion.add(currency_Dollar);
        listOfQuestion.add(currency_Euro);
        listOfQuestion.add(time_zone_Central_European);
        listOfQuestion.add(unitary_state);
        listOfQuestion.add(parlamentary_state);
        listOfQuestion.add(republic_state);
        listOfQuestion.add(time_zone_Eastern_European);
        listOfQuestion.add(motto);
        listOfQuestion.add(foundation_year);
        listOfQuestion.add(population_density);
        country_found = 0;
        useless_question = false;
        number_of_questions_left = listOfQuestion.size();
    }

    public void start() {
        Scanner keyboard = new Scanner(System.in);
        String answser;
        Question last_question;
        String filters = "";
        String predicates = "";

        System.err.println("Game has started");

        System.out.println("\n-------Initial Query----------\n");
        System.out.println(initialQuery_String_aux);

        for (Question a : listOfQuestion) {
            a.setEntropy(compute_Entropy(a, true));
        }


        last_question = getQuestion();
        System.err.println("====Selected Question ==== " + last_question.getQuestion() + "\n");


        while (number_of_questions_left > 0 && country_found != 1 && useless_question == false) {

            answser = keyboard.nextLine();
            if (answser.equals("YES") || answser.equals("yes")) {
                last_question.setPostive(true);
                System.err.println("You answer was  YES ");

            } else if (answser.equals("NO") || answser.equals("no")) {
                last_question.setPostive(false);

                System.err.println("Your answer was NO");
            } else if (answser.equals("quit")) {
                break;
            } else {
                System.err.println("Wrong Input RESTART GAME");

            }
            for (Question a : listOfQuestion) {
                if (a.getUsed() == true) {
                    if (a.getPostive() == true) {
                        predicates = predicates + a.getPredicate();
                        filters = filters + a.getFilter();
                    } else {

                        filters = filters + a.getNegate_filter();
                    }
                }
            }


            Question aux_ques = new Question("asd", predicates, filters, predicates + filters);

            ParameterizedSparqlString general_query1 = new ParameterizedSparqlString(initialQuery_String_aux + aux_ques.getPredicate() + aux_ques.getFilter() + "}");
            if (printQuery)
                System.out.println(general_query1);

            country_found = countResults(general_query1);
            number_of_questions_left--;
            System.out.println("COUNTRIES :" + country_found);

            if (printResults) {
                QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", general_query1.asQuery());
                ResultSet results = exec.execSelect();
                ResultSetFormatter.out(results);
            }


            useless_question = true;
            for (Question a : listOfQuestion) {
                if (a.getUsed() == false) {
                    Question aux_question = new Question(a.getQuestion(), a.getPredicate() + predicates, a.getFilter() + filters, predicates + a.getNegate_filter() + filters);

                    ParameterizedSparqlString general_query = new ParameterizedSparqlString(initialQuery_String_aux + aux_question.getNegate_filter() + "}");
                    if (compute_Entropy(aux_question, false) > 0.001f) {
                        useless_question = false;
                    }
                    a.setEntropy(compute_Entropy(aux_question, true));
                }

            }
            predicates = "";
            filters = "";

            last_question = getQuestion();
           if(number_of_questions_left > 0 && country_found != 1 && useless_question == false )
               System.err.println("==== Selected Question ==== " + last_question.getQuestion() + "\n");

        }
        System.out.println(country_found + " " + number_of_questions_left);

        if (country_found == 1) {
            System.out.println("that was too easy");
                MusicStuff win=new MusicStuff();
                win.playMusic("song1.wav");
        } else {
            MusicStuff win=new MusicStuff();
            win.playMusic("song1.wav");
            System.out.println("hmm... i guess your too good for me ");
        }
    }

    public double compute_Entropy(Question questions, boolean print) {
        ParameterizedSparqlString positive_query = new ParameterizedSparqlString(initialQuery_String_aux + questions.getPredicate() + questions.getFilter() + "}");
        ParameterizedSparqlString negative_query = new ParameterizedSparqlString(initialQuery_String_aux + questions.getNegate_filter() + "}");
        //ySystem.out.println(positive_query);

        int x = countResults(positive_query);
        int y = countResults(negative_query);


        if (x == 0 || y == 0) {
            if (printQuestions && print) {
                System.out.println(questions.getQuestion());
                System.out.println(x + " " + y);
                System.out.println(0.000001f);
            }
            return 0.00001f;
        } else {
            double x_procent = x * 1.f / number_of_Countries;
            double y_procent = y * 1.f / number_of_Countries;
            //System.out.println("computeEntropy\n\n"+positive_query+"\n\n"+negative_query);
            if (printQuestions && print) {
                System.out.println(questions.getQuestion() );
                System.out.println(x + " " + y);
                // System.out.println(x_procent+" "+y_procent);
                System.out.println(-(x_procent * (Math.log10(x_procent) / Math.log10(2)) + y_procent * (Math.log10(y_procent) / Math.log10(2))));
                System.out.println("\n---------\n");
            }


            return -(x_procent * (Math.log10(x_procent) / Math.log10(2)) + y_procent * (Math.log10(y_procent) / Math.log10(2)));
        }

    }

    public int countResults(ParameterizedSparqlString query) {
        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query.asQuery());
        ResultSet results = exec.execSelect();
        int i = 0;
        while (results.hasNext()) {
            i++;
            results.next();

        }
        return i;

    }

    public Question getQuestion() {
        double maxim = 0;
        Question aux = null;

        for (Question a : listOfQuestion) {
            if (a.getUsed() == false) {
                if (maxim < a.getEntropy()) {
                    maxim = a.getEntropy();
                    aux = a;
                }
            }
        }
        aux.setUsed(true);
        return aux;
    }

}
