
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Streams {
    public static void main(String[] args) throws IOException{

        //1.Integer Stream
        IntStream   //IntStream Class
                .range(1,10)    //Intermediate Operation range from 1 to 10 (where 10 is non-inclusive means 10 wont be in the output
                .forEach(System.out::println);  //Terminal Operation
                    //we are calling the print function with in the System.out class using the Double colon(::)
        System.out.println();

        //1.2 Integer Stream with Skipping first 5 digits
        IntStream
                .range ( 1,10)
                .skip ( 3 )  //Using the Skip method we get to skip that many numbers specified there
                .forEach (System.out::println);
        System.out.println();


        //1.3 Integer Stream with using simple lambda expression
        IntStream
                .range ( 1,20 )
                .skip ( 15 )
                .forEach ( x -> System.out.println ( x ) );
                    //a simple lambda expression to print X's value that went through the stream
                    // x basically is the parameter that is passed into before each function which is series of integers
        System.out.println (  );

        //1.4 Integer Stream with using Sum as Intermediate Operation within the Sysout statement.
        System.out.println(
                IntStream
                        .range ( 1,20 )
                        .skip(5)
                        .sum()
                       );
        System.out.println (  );

        //1.5 we use Stream.of(we can stream integers, strings, floating point values / even objects), sorted, and findFirst
        Stream.of ( 2,3,4, 0, 1, -1, -2)
                .sorted () //we can also do reverse order
                .findFirst () //we are ogonna find the first item using the find First()
                .ifPresent ( System.out::println );
        System.out.println (  );

        Stream.of ( "A", "B", "C", "D", "E" , "D", "F", "G")
                .sorted () //we can also do reverse order
                .findFirst () //we are gonna find the first item using the find First()
                .ifPresent ( System.out::println );
        System.out.println (  );


        //1.6 Stream from an Array, Sort, Filter, and Print
        String[] person = new String[]{"A!", "Sankit", "Sai", "Sindhu", "Vasudha", "Pavan", "Anil Bro", "pava"};
        stream( person )
                        .filter ( x -> x.contains ("s") ) //here we are filtering the names that contains S
                        .sorted ()
                        .forEach ( System.out::println );
        System.out.println();


        String[] name1 = {"A!", "Sankit", "Sai", "Sindhu", "Vasudha", "Pavan", "Anil Bro", "pava"};
        Stream.of( name1)
                .filter ( x -> x.startsWith ( "S") ) //here we are filtering the names that contains S
                .sorted ()
                .forEach ( System.out::println );
        System.out.println();

        //1.7 Avergae of Squares of an int array
       Arrays.stream ( new int[] { 2,4,6,8,0,10}) // we are using arrays.streams to stream these integers
                .map ( x -> x * x ) // here we are using map to map each item / integer to it's square
                    // SO, we are taking X as arguments in our lambda function and then we return x*x
                .average() // here we are taking average of those elements.
                .ifPresent ( System.out::println );
        System.out.println();


        //1.8 Streams from list, filter and print
        List<String> people = Arrays.asList( "AI", "Sindhu", "Vasudha", "Sai" , "Gautham", "Raj BUDUUU"); //we are streaming from a list

        people
                .stream ()  //streaming, and this starts the streams of names
                .map ( String :: toLowerCase )  // in this function we have to make use of a Class (String) :: and then the function we want to map them with in this case its toLowerCase
                .filter ( x -> x.contains ( "a" ) )
                .map ( String :: toUpperCase)
                .filter ( x -> x.contains ( "S" ) ) //here we are filtering the streams of array list which contains the letter S, A, V
                    //this is simple lambda function in which X is an argument/String that is psassed as in input and its gonna return x items which start with an A, S, V
                .filter ( x -> x.contains ( "V" ) )
                .forEach ( System.out::println);
        System.out.println();


        //1.9 Streams rows from a text file, sort, filter and print
        Stream<String> names = Files.lines ( Paths.get("/Users/snanduri/Documents/workspace-spring-tool-suite-4-4.0.0.RELEASE/StreamsPractice/src/names.txt"));
        /*we have created a Stream object of String and we are gonna call that bands, we are using Files.lines to create our stream
            So, Files.lines is gonna give us the stream of String for each line of the file and to do that we have to pass in the argument of path which is the path for the name our file.
         */
        names
                .unordered () //we can use this to sort or not
                .filter(x -> x.length()>0) // to filter the names that are more than 0 length
                .map ( String :: toUpperCase )
                .forEach(System.out ::println);
        names.close(); // it is important to close the names file
        System.out.println (  );


        //1.10 Streams rows from the text file and save to the list
       List<String> names2 = Files.lines ( Paths.get ( "/Users/snanduri/Documents/workspace-spring-tool-suite-4-4.0.0.RELEASE/StreamsPractice/src/names.txt") )
        .filter ( x -> x.startsWith ( "S" )) //filktering the names that starts with S
        .collect( java.util.stream.Collectors.toList()); //we have a collector and we r gonna add all the names that start with S into a list and we are gonna terminate that stream operation
        names2.forEach ( x -> System.out.println(x) );  // we are gonna start an other stream operation names2.forEach (Terminal Operation) where we receive and argument x and print out that item.
        //so basically printing out each item that contains S

        System.out.println();

        //1.11 Stream rows from a CSV File and count the good rows
        //we are using streams of strings which is the rows of files, we are using files.lines to get the stream of rows. Pass in the path of data file that we want to
        Stream<String> rows1 = Files.lines ( Paths.get("/Users/snanduri/Documents/workspace-spring-tool-suite-4-4.0.0.RELEASE/StreamsPractice/src/data.csv") );
        int rowCount = (int)rows1 //here, we are gonna set our row count equal to count of this operation here
                                .map ( x -> x.split ( "," ) )//so, we are using map function to split our rows in the data file. X is a stream of the row , X.split() gives us an array to split @Commas(,)
                                .filter ( x -> x.length == 3 )    // here we are gonna filter out that have the length greater than 2 in the array using x.length > 2
                                .count ();// here we count them, count is our terminal operation that reduces all the data into a single  item
        System.out.println ( rowCount + " rows." );

        /*rows1
                    .map ( x -> x.split ( "," ) )
                    .filter ( x -> x.length >2)
                    .filter ( x -> Integer.parseInt ( x[1] ) >15 )
                    .forEach ( x -> System.out.println(x[0] + " " + x[1] + " " + x[2]) );*/

        rows1.close ( );
        System.out.println();

        //1.12 Stream rows from csv file, parse data from rows and print all the rows which have the first column numbers greater than 15
        Stream<String> rows = Files.lines ( Paths.get ("/Users/snanduri/Documents/workspace-spring-tool-suite-4-4.0.0.RELEASE/StreamsPractice/src/data.csv"));
                //here, we are streaming rows form the same csv file, using the same stream operation
        rows
                 .map ( x -> x.split ( "," ) )//we are gonna split at the commas, in the data file
                    .filter ( x -> x.length >2) //we are gonna filter out bad rows means those rows which are incomplete
                    .filter ( x -> Integer.parseInt ( x[1] ) >15 ) // here, we are converting 2nd column of each row into integer, so we have Integer.parseInt with item number 1 of that array from the data file which is a column 1
                                        //we are gonna filter out those are greater than 15
                    .forEach ( x -> System.out.println(x[0] + " " + x[1] + " " + x[2]) ); //we are gonna print those rows each column 0,1,2

        rows.close ();
        System.out.println ( );


        //1.13 Streams rows from the same csv file , store the fields in HashMap
        Stream<String> rows3 = Files.lines ( Paths.get ( "/Users/snanduri/Documents/workspace-spring-tool-suite-4-4.0.0.RELEASE/StreamsPractice/src/data.csv" ) );
        java.util.Map<String, Integer> map = new java.util.HashMap <> (  );
        map = rows3
                    .map ( x -> x.split ( "," ) )//we are gonna split at the commas, in the data file
                    .filter ( x -> x.length ==3 )//we are gonna filter out bad rows means those rows which are incomplete
                    .filter ( x -> Integer.parseInt ( x[1] )> 0  )// here, we are converting 2nd column of each row into integer, so we have Integer.parseInt with item number 1 of that array from the data file which is a column 1
                                                    //we are gonna filter out those are greater than 0
                    .collect ( java.util.stream.Collectors.toMap ( //we are onna use collectors.toMap this is having 2 lambda functions in it
                                x -> x[0],  //this lambda function is for receiving x of row array and returns the first item in that row array
                            x -> Integer.parseInt ( x[1] ) // this lambda function is for receiving the row array and parses out the integer value from the 1th item in the row array
                    ) );
        rows3.close ();
        for(String key : map.keySet ())//for loop for printing the key and value
        {
            System.out.println ( key + " " + map.get ( key ) );
        }
        System.out.println (  );


        //1.14 Reduction - sum
        double total =  Stream.of(7.3, 1.5, 4.8)
                                                .reduce ( 0.0, (Double a, Double b) -> a + b );
        System.out.println ( "Total  : " + total );
        System.out.println (  );


        //1.15 Reduction - Summary Statistics
        java.util.IntSummaryStatistics summary = IntStream.of(7,2,19,73,88, 4, 10)
                            .summaryStatistics ();
        System.out.println ( "Summary  : " + summary );
        System.out.println ();

    }
}