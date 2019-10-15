package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Tokenizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public abstract class BaseAST {
    public ArrayList<BaseAST> children = new ArrayList<>();
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();
}
