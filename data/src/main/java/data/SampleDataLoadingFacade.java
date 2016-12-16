package data;

import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author pato
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}
