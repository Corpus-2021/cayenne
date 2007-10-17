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

package org.apache.cayenne.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.cayenne.util.CayenneMapEntry;
import org.apache.cayenne.util.Util;
import org.apache.cayenne.util.XMLEncoder;
import org.apache.cayenne.util.XMLSerializable;

/**
 * A mapping descriptor for a database stored procedure.
 * 
 * @author Andrus Adamchik
 */
public class Procedure implements CayenneMapEntry, XMLSerializable, Serializable {

    protected String name;
    protected DataMap dataMap;

    protected String catalog;
    protected String schema;
    protected boolean returningValue;
    protected List callParameters = new ArrayList();

    /**
     * Creates an unnamed procedure object.
     */
    public Procedure() {
    }

    /**
     * Creates a named Procedure object.
     */
    public Procedure(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParent() {
        return getDataMap();
    }

    public void setParent(Object parent) {
        if (parent != null && !(parent instanceof DataMap)) {
            throw new IllegalArgumentException("Expected null or DataMap, got: " + parent);
        }

        setDataMap((DataMap) parent);
    }

    /**
     * Prints itself as XML to the provided XMLEncoder.
     * 
     * @since 1.1
     */
    public void encodeAsXML(XMLEncoder encoder) {
        encoder.print("<procedure name=\"");
        encoder.print(Util.encodeXmlAttribute(getName()));
        encoder.print('\"');

        if (getSchema() != null && getSchema().trim().length() > 0) {
            encoder.print(" schema=\"");
            encoder.print(getSchema().trim());
            encoder.print('\"');
        }

        if (getCatalog() != null && getCatalog().trim().length() > 0) {
            encoder.print(" catalog=\"");
            encoder.print(getCatalog().trim());
            encoder.print('\"');
        }

        if (isReturningValue()) {
            encoder.print(" returningValue=\"true\"");
        }

        encoder.println('>');

        encoder.indent(1);
        encoder.print(getCallParameters());
        encoder.indent(-1);

        encoder.println("</procedure>");
    }

    /**
     * Returns procedure name including schema, if present.
     */
    public String getFullyQualifiedName() {
        return (schema != null) ? schema + '.' + getName() : getName();
    }

    /**
     * @return parent DataMap of this entity.
     */
    public DataMap getDataMap() {
        return dataMap;
    }

    /**
     * Sets parent DataMap of this entity.
     */
    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public void setCallParameters(List parameters) {
        clearCallParameters();
        callParameters.addAll(parameters);
    }

    /**
     * Adds new call parameter to the stored procedure. Also sets <code>param</code>'s
     * parent to be this procedure.
     */
    public void addCallParameter(ProcedureParameter param) {
        if (param.getName() == null) {
            throw new IllegalArgumentException("Attempt to add unnamed parameter.");
        }

        if (callParameters.contains(param)) {
            throw new IllegalArgumentException(
                    "Attempt to add the same parameter more than once:" + param);
        }

        param.setProcedure(this);
        callParameters.add(param);
    }

    /** Removes a named call parameter. */
    public void removeCallParameter(String name) {
        for (int i = 0; i < callParameters.size(); i++) {
            ProcedureParameter nextParam = (ProcedureParameter) callParameters.get(i);
            if (name.equals(nextParam.getName())) {
                callParameters.remove(i);
                break;
            }
        }
    }

    public void clearCallParameters() {
        callParameters.clear();
    }

    /**
     * Returns an unmodifiable list of call parameters.
     */
    public List getCallParameters() {
        return Collections.unmodifiableList(callParameters);
    }

    /**
     * Returns a list of OUT and INOUT call parameters. If procedure has a return value,
     * it will also be included as a call parameter.
     */
    public List getCallOutParameters() {
        List outParams = new ArrayList(callParameters.size());
        Iterator it = callParameters.iterator();
        while (it.hasNext()) {
            ProcedureParameter param = (ProcedureParameter) it.next();
            if (param.isOutParam()) {
                outParams.add(param);
            }
        }

        return outParams;
    }

    /**
     * Returns parameter describing the return value of the StoredProcedure, or null if
     * procedure does not support return values. If procedure supports return parameters,
     * its first parameter is always assumed to be a return result.
     */
    public ProcedureParameter getResultParam() {
        // if procedure returns parameters, this must be the first parameter
        // otherwise, return null
        return (returningValue && callParameters.size() > 0)
                ? (ProcedureParameter) callParameters.get(0)
                : null;
    }

    /**
     * Returns <code>true</code> if a stored procedure returns a value. The first
     * parameter in a list of parameters will be assumed to be a descriptor of return
     * value.
     * 
     * @return boolean
     */
    public boolean isReturningValue() {
        return returningValue;
    }

    public void setReturningValue(boolean returningValue) {
        this.returningValue = returningValue;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getSchema() {
        return schema;
    }

    /**
     * Sets stored procedure's catalog.
     */
    public void setCatalog(String string) {
        catalog = string;
    }

    /**
     * Sets stored procedure's database schema.
     */
    public void setSchema(String string) {
        schema = string;
    }
}
