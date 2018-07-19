package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;

public interface IPointGetter<T> {

    Point getMovePoint(final Field<T> field);

}
