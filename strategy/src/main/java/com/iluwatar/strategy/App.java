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

package com.iluwatar.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>The Strategy pattern (also known as the policy pattern) is a software design pattern that
 * enables an algorithm's behavior to be selected at runtime.</p>
 *
 * <p>Before Java 8 the Strategies needed to be separate classes forcing the developer
 * to write lots of boilerplate code. With modern Java it is easy to pass behavior
 * with method references and lambdas making the code shorter and more readable.</p>
 *
 * <p>In this example ({@link DragonSlayingStrategy}) encapsulates an algorithm. The containing
 * object ({@link DragonSlayer}) can alter its behavior by changing its strategy.</p>
 * 
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  
  /**
   * Program entry point.
   * 
   * @param args command line args
   */
  public static void main(String[] args) {
    // GoF Strategy pattern
//    LOGGER.info("前方发现绿龙!");
    var dragonSlayer = new DragonSlayer(new MeleeStrategy());
    dragonSlayer.goToBattle();
    LOGGER.info("红龙出现.");
    dragonSlayer.changeStrategy(new ProjectileStrategy());
    dragonSlayer.goToBattle();
    LOGGER.info("黑龙降临在你面前.");
    dragonSlayer.changeStrategy(new SpellStrategy());
    dragonSlayer.goToBattle();

    // Java 8 Strategy pattern
    LOGGER.info("前方发现绿龙!");
    dragonSlayer = new DragonSlayer(
        () ->{ LOGGER.info("用神剑将龙的头弄坏!");
          System.out.println("匿名类");});
    dragonSlayer.goToBattle();
    LOGGER.info("Red dragon emerges.");
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "你用魔法cross射击龙，它掉在地上死了!"));
    dragonSlayer.goToBattle();
    LOGGER.info("黑龙降临在你面前.");
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "你投下了瓦解的魔咒，龙在一堆灰尘中蒸发了!"));
    dragonSlayer.goToBattle();
  }
}
