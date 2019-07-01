import org.junit.Test;

import static cleancode.imdb.command.CommandTokenizer.tokenize;
import static org.junit.Assert.assertEquals;

public class CommandSplittingTest {
    @Test
    public void getCommandName() throws Exception {
        String command = "get-movie";
        assertEquals(command, tokenize(command).get(0));
    }

    @Test
    public void getCommandArgument() throws Exception {
        String command = "get-movie Inception";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("Inception", tokenize(command).get(1));
    }

    @Test
    public void getQuotedCommandArgumentContainingSpace() throws Exception {
        String command = "get-movie \"Ender's Game\"";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("Ender's Game", tokenize(command).get(1));
    }

    @Test
    public void getCommandOptions() throws Exception {
        String command = "get-movie Inception --fields Title";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("Inception", tokenize(command).get(1));
        assertEquals("--fields", tokenize(command).get(2));
        assertEquals("Title", tokenize(command).get(3));
    }

    @Test
    public void getCommandOptionsWithMultipleArguments() throws Exception {
        String command = "get-movie Inception --fields Title Genre";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("Inception", tokenize(command).get(1));
        assertEquals("--fields", tokenize(command).get(2));
        assertEquals("Title", tokenize(command).get(3));
        assertEquals("Genre", tokenize(command).get(4));
    }

    @Test
    public void getCommandOptionsWithCommaSeparatedArguments() throws Exception {
        String command = "get-movie Inception --fields Title, Genre";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("Inception", tokenize(command).get(1));
        assertEquals("--fields", tokenize(command).get(2));
        assertEquals("Title", tokenize(command).get(3));
        assertEquals("Genre", tokenize(command).get(4));
    }

    @Test
    public void ignoresOptionsEqualSign() throws Exception {
        String command1 = "get-movie Inception --fields=Title, Genre";
        assertEquals("get-movie", tokenize(command1).get(0));
        assertEquals("Inception", tokenize(command1).get(1));
        assertEquals("--fields", tokenize(command1).get(2));
        assertEquals("Title", tokenize(command1).get(3));
        assertEquals("Genre", tokenize(command1).get(4));

        String command2 = "get-movie Inception --fields=Title, Genre";
        assertEquals("get-movie", tokenize(command2).get(0));
        assertEquals("Inception", tokenize(command2).get(1));
        assertEquals("--fields", tokenize(command2).get(2));
        assertEquals("Title", tokenize(command2).get(3));
        assertEquals("Genre", tokenize(command2).get(4));
    }

    @Test
    public void preservesQuotesPunctuation() throws Exception {
        String command = "get-movie \"The Good, the Bad and the Ugly\"";
        assertEquals("get-movie", tokenize(command).get(0));
        assertEquals("The Good, the Bad and the Ugly", tokenize(command).get(1));
    }

}
