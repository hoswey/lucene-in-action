package lia.searching;

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

import lia.extsearch.queryparser.NumericQueryParserTest.NumericDateRangeQueryParser;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// From chapter 6
public class SearchServletFragment extends HttpServlet {

    private IndexSearcher searcher;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QueryParser parser = new NumericDateRangeQueryParser(Version.LUCENE_30, "contents",
                new StandardAnalyzer(Version.LUCENE_30));

        parser.setLocale(request.getLocale());
        parser.setDateResolution(DateTools.Resolution.DAY);

        Query query = null;
        try {
            query = parser.parse(request.getParameter("q"));
        } catch (ParseException e) {
            e.printStackTrace(System.err);  // 1
        }

        TopDocs docs = searcher.search(query, 10);        // 2
    }
  /*
    1 Handle exception
    2 Perfom search and render results
  */
}
