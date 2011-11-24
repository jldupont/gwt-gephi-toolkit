/*
Copyright 2008-2010 Gephi
Authors : Helder Suzuki <heldersuzuki@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.gephi.layout.plugin.multilevel;

import org.gephi.graph.api.HierarchicalGraph;
import org.gephi.layout.plugin.AbstractLayout;
import org.gephi.layout.spi.Layout;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.force.yifanHu.YifanHuProportional;
import org.gephi.layout.plugin.random.RandomLayout;

/**
 *
 * @author Helder Suzuki <heldersuzuki@gephi.org>
 */
public class MultiLevelLayout extends AbstractLayout implements Layout {

    private HierarchicalGraph graph;
    private int level;
    private YifanHuLayout layout;
    private YifanHuProportional yifanHu;
    private CoarseningStrategy coarseningStrategy;
    private int minSize;
    private double minCoarseningRate;
    private float stepRatio;
    private float optimalDistance;
    private int quadTreeMaxLevel;
    private float barnesHutTheta;

    //Security
    private int initedView;

    public MultiLevelLayout(CoarseningStrategy coarseningStrategy) {
        super();
        this.coarseningStrategy = coarseningStrategy;
        //     this.yifanHu = new YifanHu();
        this.yifanHu = new YifanHuProportional();
    }

    public void initAlgo() {
        graph = graphModel.getHierarchicalGraphVisible();
        initedView = graph.getView().getViewId();
        setConverged(false);
        level = 0;

        while (true) {
            int graphSize = graph.getTopNodes().toArray().length;
            coarseningStrategy.coarsen(graph);
            level++;
            int newGraphSize = graph.getTopNodes().toArray().length;
            if (newGraphSize < getMinSize() || newGraphSize > graphSize * getMinCoarseningRate()) {
                break;
            }
        }

        Layout random = new RandomLayout(1000);
        random.setGraphModel(graphModel);
        random.initAlgo();
        random.goAlgo();

        initYifanHu();
    }

    void initYifanHu() {
        layout = yifanHu.buildLayout();
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.setAdaptiveCooling(false);
        layout.setStepRatio(stepRatio);
        layout.setOptimalDistance(optimalDistance);
        layout.setBarnesHutTheta(barnesHutTheta);
        layout.setQuadTreeMaxLevel(quadTreeMaxLevel);
        layout.initAlgo();
    }

    public void goAlgo() {
        HierarchicalGraph newGraph = graphModel.getHierarchicalGraphVisible();
        if(newGraph.getView().getViewId()!=initedView) {
            setConverged(true);
            layout.endAlgo();
            endAlgo();
            return;
        }
        this.graph = newGraph;
        if (layout.canAlgo()) {
            layout.goAlgo();
        } else {
            layout.endAlgo();
            if (level > 0) {
                coarseningStrategy.refine(graph);
                level--;

                initYifanHu();
            } else {
                setConverged(true);
                layout = null;
            }
        }
    }

    public void endAlgo() {
        while (level > 0) {
            coarseningStrategy.refine(graph);
            level--;
        }
    }

    public void resetPropertiesValues() {
        setMinSize(3);
        setMinCoarseningRate(0.75d);
        setStepRatio(0.97f);
        setOptimalDistance(100f);
        setQuadTreeMaxLevel(10);
        setBarnesHutTheta(1.2f);
    }
    
    /**
     * @return the minSize
     */
    public Integer getMinSize() {
        return minSize;
    }

    /**
     * @param minSize the minSize to set
     */
    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    /**
     * @return the minCoarseningRate
     */
    public Double getMinCoarseningRate() {
        return minCoarseningRate;
    }

    /**
     * @param minCoarseningRate the minCoarseningRate to set
     */
    public void setMinCoarseningRate(Double minCoarseningRate) {
        this.minCoarseningRate = minCoarseningRate;
    }

    /**
     * @return the stepRatio
     */
    public Float getStepRatio() {
        return stepRatio;
    }

    /**
     * @param stepRatio the stepRatio to set
     */
    public void setStepRatio(Float stepRatio) {
        this.stepRatio = stepRatio;
    }

    /**
     * @return the optimalDistance
     */
    public Float getOptimalDistance() {
        return optimalDistance;
    }

    /**
     * @param optimalDistance the optimalDistance to set
     */
    public void setOptimalDistance(Float optimalDistance) {
        this.optimalDistance = optimalDistance;
    }

    /**
     * @return the quadTreeMaxLevel
     */
    public Integer getQuadTreeMaxLevel() {
        return quadTreeMaxLevel;
    }

    /**
     * @param quadTreeMaxLevel the quadTreeMaxLevel to set
     */
    public void setQuadTreeMaxLevel(Integer quadTreeMaxLevel) {
        this.quadTreeMaxLevel = quadTreeMaxLevel;
    }

    /**
     * @return the barnesHutTheta
     */
    public Float getBarnesHutTheta() {
        return barnesHutTheta;
    }

    /**
     * @param barnesHutTheta the barnesHutTheta to set
     */
    public void setBarnesHutTheta(Float barnesHutTheta) {
        this.barnesHutTheta = barnesHutTheta;
    }

    public interface CoarseningStrategy {

        public void coarsen(HierarchicalGraph graph);

        public void refine(HierarchicalGraph graph);
    }
}
