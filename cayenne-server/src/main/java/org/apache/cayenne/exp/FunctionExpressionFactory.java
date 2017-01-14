/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.exp;

import org.apache.cayenne.exp.parser.ASTAbs;
import org.apache.cayenne.exp.parser.ASTAvg;
import org.apache.cayenne.exp.parser.ASTConcat;
import org.apache.cayenne.exp.parser.ASTCount;
import org.apache.cayenne.exp.parser.ASTLength;
import org.apache.cayenne.exp.parser.ASTLocate;
import org.apache.cayenne.exp.parser.ASTLower;
import org.apache.cayenne.exp.parser.ASTMax;
import org.apache.cayenne.exp.parser.ASTMin;
import org.apache.cayenne.exp.parser.ASTMod;
import org.apache.cayenne.exp.parser.ASTObjPath;
import org.apache.cayenne.exp.parser.ASTScalar;
import org.apache.cayenne.exp.parser.ASTSqrt;
import org.apache.cayenne.exp.parser.ASTSubstring;
import org.apache.cayenne.exp.parser.ASTSum;
import org.apache.cayenne.exp.parser.ASTTrim;
import org.apache.cayenne.exp.parser.ASTUpper;

/**
 * Collection of factory methods to create function call expressions.
 *
 * @since 4.0
 */
public class FunctionExpressionFactory {

    /**
     * Call SUBSTRING(string, offset, length) function
     *
     * @param exp expression that must evaluate to string
     * @param offset start offset of substring
     * @param length length of subtring
     * @return SUBSTRING() call expression
     */
    public static Expression substringExp(Expression exp, int offset, int length) {
        return substringExp(exp, new ASTScalar((Integer)offset), new ASTScalar((Integer)length));
    }

    /**
     * Call SUBSTRING(string, offset, length) function
     *
     * @param path Object path value
     * @param offset start offset of substring
     * @param length length of subtring
     * @return SUBSTRING() call expression
     */
    public static Expression substringExp(String path, int offset, int length) {
        return substringExp(new ASTObjPath(path), new ASTScalar((Integer)offset), new ASTScalar((Integer)length));
    }

    /**
     * Call SUBSTRING(string, offset, length) function
     *
     * @param exp expression that must evaluate to string
     * @param offset start offset of substring must evaluate to int
     * @param length length of subtring must evaluate to int
     * @return SUBSTRING() call expression
     */
    public static Expression substringExp(Expression exp, Expression offset, Expression length) {
        return new ASTSubstring(exp, offset, length);
    }

    /**
     * @param exp string expression to trim
     * @return TRIM() call expression
     */
    public static Expression trimExp(Expression exp) {
        return new ASTTrim(exp);
    }

    /**
     * @param path object path value
     * @return TRIM() call expression
     */
    public static Expression trimExp(String path) {
        return new ASTTrim(new ASTObjPath(path));
    }

    /**
     * @param exp string expression
     * @return LOWER() call expression
     */
    public static Expression lowerExp(Expression exp) {
        return new ASTLower(exp);
    }

    /**
     * @param path object path value
     * @return LOWER() call expression
     */
    public static Expression lowerExp(String path) {
        return new ASTLower(new ASTObjPath(path));
    }

    /**
     * @param exp string expression
     * @return UPPER() call expression
     */
    public static Expression upperExp(Expression exp) {
        return new ASTUpper(exp);
    }

    /**
     * @param path object path value
     * @return UPPER() call expression
     */
    public static Expression upperExp(String path) {
        return new ASTUpper(new ASTObjPath(path));
    }

    /**
     * @param exp string expression
     * @return LENGTH() call expression
     */
    public static Expression lengthExp(Expression exp) {
        return new ASTLength(exp);
    }

    /**
     * @param path object path value
     * @return LENGTH() call expression
     */
    public static Expression lengthExp(String path) {
        return new ASTLength(new ASTObjPath(path));
    }

    /**
     * Call LOCATE(substring, string) function that return position
     * of substring in string or 0 if it is not found.
     *
     * @param substring object path value
     * @param exp string expression
     * @return LOCATE() call expression
     */
    public static Expression locateExp(String substring, Expression exp) {
        return locateExp(new ASTScalar(substring), exp);
    }

    /**
     * Call LOCATE(substring, string) function that return position
     * of substring in string or 0 if it is not found.
     *
     * @param substring object path value
     * @param path object path
     * @return LOCATE() call expression
     */
    public static Expression locateExp(String substring, String path) {
        return locateExp(new ASTScalar(substring), new ASTObjPath(path));
    }

    /**
     * Call LOCATE(substring, string) function that return position
     * of substring in string or 0 if it is not found.
     *
     * @param substring string expression
     * @param exp string expression
     * @return LOCATE() call expression
     */
    public static Expression locateExp(Expression substring, Expression exp) {
        return new ASTLocate(substring, exp);
    }

    /**
     * @param exp numeric expression
     * @return ABS() call expression
     */
    public static Expression absExp(Expression exp) {
        return new ASTAbs(exp);
    }

    /**
     * @param path object path value
     * @return ABS() call expression
     */
    public static Expression absExp(String path) {
        return new ASTAbs(new ASTObjPath(path));
    }

    /**
     * @param exp numeric expression
     * @return SQRT() call expression
     */
    public static Expression sqrtExp(Expression exp) {
        return new ASTSqrt(exp);
    }

    /**
     * @param path object path value
     * @return SQRT() call expression
     */
    public static Expression sqrtExp(String path) {
        return new ASTSqrt(new ASTObjPath(path));
    }

    /**
     * @param exp numeric expression
     * @param number divisor
     * @return MOD() call expression
     */
    public static Expression modExp(Expression exp, Number number) {
        return modExp(exp, new ASTScalar(number));
    }

    /**
     * @param path object path value
     * @param number divisor
     * @return MOD() call expression
     */
    public static Expression modExp(String path, Number number) {
        return modExp(new ASTObjPath(path), new ASTScalar(number));
    }

    /**
     * @param exp object path value
     * @param number numeric expression
     * @return MOD() call expression
     */
    public static Expression modExp(Expression exp, Expression number) {
        return new ASTMod(exp, number);
    }

    /**
     * Factory method for expression to call CONCAT(string1, string2, ...) function
     * Can be used like:
     *  Expression concat = concatExp(SomeClass.POPERTY_1.getPath(), SomeClass.PROPERTY_2.getPath());
     *
     * SQL generation note:
     * - if DB supports CONCAT function with vararg then it will be used
     * - if DB supports CONCAT function with two args but also supports concat operator, then operator (eg ||) will be used
     * - if DB supports only CONCAT function with two args then it will be used what can lead to SQL exception if
     * used with more than two arguments
     *
     * Currently only known DB with limited concatenation functionality is Openbase.
     *
     * @param expressions array of expressions
     * @return CONCAT() call expression
     */
    public static Expression concatExp(Expression... expressions) {
        if(expressions == null || expressions.length == 0) {
            return new ASTConcat();
        }

        return new ASTConcat(expressions);
    }

    /**
     * Factory method for expression to call CONCAT(string1, string2, ...) function
     * Can be used like:
     *  Expression concat = concatExp("property1", "property2");
     *
     * SQL generation note:
     * - if DB supports CONCAT function with vararg then it will be used
     * - if DB supports CONCAT function with two args but also supports concat operator, then operator (eg ||) will be used
     * - if DB supports only CONCAT function with two args then it will be used what can lead to SQL exception if
     * used with more than two arguments
     *
     * Currently only known DB with limited concatenation functionality is Openbase.
     *
     * @param paths array of paths
     * @return CONCAT() call expression
     */
    public static Expression concatExp(String... paths) {
        if(paths == null || paths.length == 0) {
            return new ASTConcat();
        }

        Expression[] expressions = new Expression[paths.length];
        for(int i=0; i<paths.length; i++) {
            expressions[i] = new ASTObjPath(paths[i]);
        }
        return new ASTConcat(expressions);
    }

    /**
     * @return Expression COUNT(&ast;)
     */
    public static Expression countExp() {
        return new ASTCount();
    }

    /**
     * @return Expression COUNT(exp)
     */
    public static Expression countExp(Expression exp) {
        return new ASTCount(exp);
    }

    /**
     * @return Expression MIN(exp)
     */
    public static Expression minExp(Expression exp) {
        return new ASTMin(exp);
    }

    /**
     * @return Expression MAX(exp)
     */
    public static Expression maxExp(Expression exp) {
        return new ASTMax(exp);
    }

    /**
     * @return Expression AVG(exp)
     */
    public static Expression avgExp(Expression exp) {
        return new ASTAvg(exp);
    }

    /**
     * @return SUM(exp) expression
     */
    public static Expression sumExp(Expression exp) {
        return new ASTSum(exp);
    }
}