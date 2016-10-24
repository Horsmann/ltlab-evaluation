/*******************************************************************************
 * Copyright 2016
 * Language Technology Lab
 * University of Duisburg-Essen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.unidue.ltl.evaluation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class HelloWorldTest
{
    @Test
    public void run()
    {

        // Simple demo of mockito - the method we call is not yet implemented but we can mock the
        // method call to return a dummy value.
        // This allows to build unit tests even if components you are depending on are not yet
        // implemented by simply mocking them and pretend like the component is there

        HelloWorld mock = Mockito.mock(HelloWorld.class);
        Mockito.when(mock.returnHelloWorld()).thenReturn("Hello World");

        assertEquals("Hello World", mock.returnHelloWorld());

    }

}
