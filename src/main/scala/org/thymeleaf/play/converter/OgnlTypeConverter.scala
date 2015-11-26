/*
 * Copyright 2015 Vitaly Koulakov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.thymeleaf.play.converter

import java.util

import ognl.DefaultTypeConverter

import scala.collection.JavaConversions._

/**
 * Class extends [[ognl.DefaultTypeConverter]] to support
 * conversion from Scala collections to Java collections counterparts.
 */
class OgnlTypeConverter extends DefaultTypeConverter {
	override def convertValue(context: util.Map[_, _], value: scala.Any, toType: Class[_]): AnyRef = {
		value match {
			case c: Seq[_] => seqAsJavaList(c)
			case c: Map[_, _] => mapAsJavaMap(c)
			case c: Set[_] => setAsJavaSet(c)
			case _ => super.convertValue(context, value, toType)
		}
	}
}
