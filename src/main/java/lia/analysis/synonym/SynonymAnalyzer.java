package lia.analysis.synonym;

/**
 * Copyright Manning Publications Co.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific lan
 */

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

// From chapter 4
public class SynonymAnalyzer extends Analyzer {

    private SynonymEngine engine;

    public SynonymAnalyzer(SynonymEngine engine) {
        this.engine = engine;
    }

    public TokenStream tokenStream(String fieldName, Reader reader) {
        TokenStream result = new SynonymFilter(new StopFilter(true,
                new LowerCaseFilter(new StandardFilter(new StandardTokenizer(Version.LUCENE_30, reader))),
                StopAnalyzer.ENGLISH_STOP_WORDS_SET), engine);
        return result;
    }
}
