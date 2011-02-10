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
package org.apache.directory.shared.ldap.model.exception;


/**
 * Thrown when a LdapStringEncodingDecoder has encountered a failure condition
 * during the String creation.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class LdapURLEncodingException extends LdapException
{
    private static final long serialVersionUID = 193461058160901616L;


    /**
     * Creates a LdapStringEncodingException
     * 
     * @param pMessage A message with meaning to a human
     */
    public LdapURLEncodingException( String pMessage )
    {
        super( pMessage );
    }
}