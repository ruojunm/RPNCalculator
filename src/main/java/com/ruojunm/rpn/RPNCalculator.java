package com.ruojunm.rpn;

import com.ruojunm.rpn.element.NumberElement;
import com.ruojunm.rpn.message.OperatorInputErrorException;
import com.ruojunm.rpn.message.MessageHelper;
import com.ruojunm.rpn.operator.BaseOperator;
import com.ruojunm.rpn.operator.OperatorCmd;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class RPNCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPNCalculator.class);

    RPNContext context = new RPNContext();

    /**
     * An API of RPN Calculator, which could be used in cmd line environment or other invokers.
     *
     * @param input The String to be processed by this RPNCalculator
     */
    public String processRPNInput(String input) {
        try {
            initContext();

            //"1 2   +   3  sqrt 4 undo    clear".split("(?=(?!^)\\s)(?<!\\s)|(?!\\s)(?<=\\s)") = String[15] { "1", " ", "2", "   ", "+", "   ", "3", "  ", "sqrt", " ", "4", " ", "undo", "    ", "clear" }
            // This kind of split can keep redundant spaces.
            // Why?  Because we want to maek product be kind of fault-tolerance.
            // Of course, split by "\\s" can also give users the correct calcuation result.
            // But unfortunately, without keeping info of the input spaces, we can't accurately tell user which index of input string is invalid.
            // So basically, this more complex splitor is designed for showing accurate error msg to users.
            String[] inputWithSpacesList = input.split("(?=(?!^)\\s)(?<!\\s)|(?!\\s)(?<=\\s)");
//            for(int i = 0; i < inputWithSpacesList.length; i++){ // as we need calculate the index, we
//
//            }

            Arrays.stream(inputWithSpacesList) // split userinput data by spaces but keeping spaces
                    .forEach(this::ProcessElement); // evaluate each Element. If there is invalid input, throw Exception and skip the rest of input
        } catch (OperatorInputErrorException e) {
            LOGGER.error("Error Input", e); // write error log into files, in case we need to collect it from users to fix problems.
            outPutErrorMsg(e);
        } catch (ArithmeticException e) {
            LOGGER.error("Error Input", e); // write error log into files, in case we need to collect it from users to fix problems.
            outPutErrorMsg(e);
        } catch (NumberFormatException e) {
            LOGGER.error("Error Input", e); // write error log into files, in case we need to collect it from users to fix problems.
            outPutErrorMsg(e);
        } finally {
            return outPutStackContent();
        }
    }

    /**
     * Init current position and operatorStc info
     */
    private void initContext() {
        this.context.currentOperatorPosition =0;
        this.context.currentOperatorStr ="";


    }

    private void ProcessElement(String s) {
        if(StringUtils.isNotBlank(s)){
            this.context.currentOperatorStr = s;
            OperatorCmd cmd = BaseOperator.newInstance(s);
            cmd.setOriginalInput(s);
            cmd.setPosition(this.context.currentOperatorPosition);
            cmd.execute(this.context);
        }
        this.context.currentOperatorPosition += s.length();


    }

    private void outPutErrorMsg(Exception e) {
        this.context.setErrorMsg(MessageHelper.getErrorMessge(e, this.context));
    }

    /**
     * output the current stack to console
     * We also return the content as method return value for the some invoker
     * which need to display the content other than the console
     *
     * @return
     */
    private String outPutStackContent() {
        String stackStr = "";
        if(StringUtils.isNotEmpty(context.getErrorMsg())){
            stackStr = context.getErrorMsg() + System.lineSeparator();
        }

        stackStr += "stack: " + context.getStack().stream().map(x -> x.toString()).collect(Collectors.joining(" "));
        System.out.println(stackStr);
        context.cleanErrorMsg();
        return stackStr;
    }


    /**
     * A calculator context, holding number data and operator data in stacks.
     * This is useful for operator instances
     */
    public class RPNContext {
        private Deque<NumberElement> stack = new ArrayDeque<>();
        private Deque<OperatorCmd> operationStack = new ArrayDeque<>();
        private String errorMsg = "";
        private int currentOperatorPosition = 0;
        private String currentOperatorStr="";

        public Deque<NumberElement> getStack() {
            return stack;
        }

        public Deque<OperatorCmd> getOperationStack() {
            return operationStack;
        }

        public void setErrorMsg(String message) {
            this.errorMsg = message;
        }

        public String getErrorMsg() {
            return this.errorMsg;
        }

        public void cleanErrorMsg() {
            this.errorMsg = "";
        }

        public int getCurrentOperatorPosition() {
            return currentOperatorPosition;
        }

        public void setCurrentOperatorPosition(int currentOperatorPosition) {
            this.currentOperatorPosition = currentOperatorPosition;
        }

        public String getCurrentOperatorStr() {
            return currentOperatorStr;
        }

        public void setCurrentOperatorStr(String currentOperatorStr) {
            this.currentOperatorStr = currentOperatorStr;
        }
    }

}
