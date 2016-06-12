package lia.analysis;

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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.io.StringReader;

// From chapter 4
public class Fragments {

    public void frag1() throws Exception {
        Directory directory = null;
        // START
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter writer = new IndexWriter(directory, analyzer, IndexWriter.MaxFieldLength.UNLIMITED);
        // END
    }

    public void frag2() throws Exception {
        IndexWriter writer = null;
        // START
        Document doc = new Document();
        doc.add(new Field("title", "This is the title", Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("contents", "...document contents...", Field.Store.NO, Field.Index.ANALYZED));
        writer.addDocument(doc);
        // END
    }

    public void frag3() throws Exception {
        Analyzer analyzer = null;
        String text = null;
        // START
        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
        PositionIncrementAttribute posIncr = (PositionIncrementAttribute) stream
                .addAttribute(PositionIncrementAttribute.class);
        while (stream.incrementToken()) {
            System.out.println("posIncr=" + posIncr.getPositionIncrement());
        }
        // END
    }
}