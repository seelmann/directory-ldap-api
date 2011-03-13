/*
 *  Licensed to the Apache Software Foundation (ASF) under one
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
 *  
 */
package org.apache.directory.shared.ldap.model.schema.normalizers;


import org.apache.directory.shared.ldap.model.constants.SchemaConstants;
import org.apache.directory.shared.ldap.model.entry.StringValue;
import org.apache.directory.shared.ldap.model.entry.Value;
import org.apache.directory.shared.ldap.model.exception.LdapException;
import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.ldap.model.schema.AbstractNormalizer;
import org.apache.directory.shared.ldap.model.schema.SchemaManager;


/**
 * Normalizer a Dn
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@SuppressWarnings("serial")
public class DnNormalizer extends AbstractNormalizer
{
    /** A reference to the schema manager used to normalize the Dn */
    private SchemaManager schemaManager;
    
    /**
     * Empty constructor
     */
    public DnNormalizer()
    {
        super( SchemaConstants.DISTINGUISHED_NAME_MATCH_MR_OID );
    }


    /**
     * {@inheritDoc}
     */
    public Value<?> normalize( Value<?> value ) throws LdapException
    {
        Dn dn = null;
        
        String dnStr = value.getString();
        
        dn = new Dn( schemaManager, dnStr );
        
        return new StringValue( dn.getNormName() );
    }


    /**
     * {@inheritDoc}
     */
    public String normalize( String value ) throws LdapException
    {
        Dn dn = null;
        
        dn = new Dn( schemaManager, value );
        
        return dn.getNormName();
    }


    /**
     * Normalize a Dn
     * @param value The Dn to normalize
     * @return A normalized Dn
     * @throws LdapException
     */
    public String normalize( Dn value ) throws LdapException
    {
        Dn dn = null;
        
        dn = value.normalize( schemaManager );
        
        return dn.getNormName();
    }


    /**
     * {@inheritDoc}
     */
    public void setSchemaManager( SchemaManager schemaManager )
    {
        this.schemaManager = schemaManager;
    }
}