/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 首长访问者，来了之后所有人都要说首长好
 * .
 */
public class PrimeMinister implements UnitVisitor {

  private static final Logger LOGGER = LoggerFactory.getLogger(PrimeMinister.class);

  @Override
  public void visit(Soldier soldier) {
    LOGGER.info("首长好  --from:{}", soldier);
  }

  @Override
  public void visit(Sergeant sergeant) {
      LOGGER.info("首长好  --from:{}", sergeant);
  }
  @Override
  public void visit(Commander commander) {
    LOGGER.info("首长好  --from:{}", commander);
  }
}

