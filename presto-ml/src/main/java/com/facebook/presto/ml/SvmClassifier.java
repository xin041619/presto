/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.ml;

import com.facebook.presto.ml.type.ModelType;
import com.google.common.base.Throwables;
import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_parameter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.facebook.presto.ml.type.ClassifierType.CLASSIFIER;
import static com.google.common.base.Preconditions.checkNotNull;

public class SvmClassifier
        extends AbstractSvmModel
        implements Classifier
{
    public SvmClassifier()
    {
    }

    private SvmClassifier(svm_model model)
    {
        super(model);
    }

    public static SvmClassifier deserialize(byte[] modelData)
    {
        // TODO do something with the hyperparameters
        try {
            svm_model model = svm.svm_load_model(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(modelData))));
            return new SvmClassifier(model);
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public int classify(FeatureVector features)
    {
        checkNotNull(model, "model is null");
        return (int) svm.svm_predict(model, toSvmNodes(features));
    }

    @Override
    public ModelType getType()
    {
        return CLASSIFIER;
    }

    @Override
    protected int getLibsvmType()
    {
        return svm_parameter.C_SVC;
    }
}
