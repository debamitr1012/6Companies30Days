class program1 {
    public int evalRPN(String[] tokens) {
    int[] ls = new int[tokens.length/2+1];
    int index = 0;
    for (String token : tokens) {
        switch (token) {
            case "+":
                ls[index - 2] = ls[index - 2] + ls[index - 1];
                index--;
                break;
            case "-":
                ls[index - 2] = ls[index - 2] - ls[index - 1];
                index--;
                break;
            case "*":
                ls[index - 2] = ls[index - 2] * ls[index - 1];
                index--;
                break;
            case "/":
                ls[index - 2] = ls[index - 2] / ls[index - 1];
                index--;
                break;
            default:
                ls[index++] = Integer.parseInt(token);
                break;
        }}
    return ls[0];
}}