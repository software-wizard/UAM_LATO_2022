package pl.psi.artifacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactRankTest {

    @Test
    void compareRanks() {
        assertEquals( ArtifactRank.MINOR.compareRanks( ArtifactRank.MINOR ), 0 );
        assertEquals( ArtifactRank.MINOR.compareRanks( ArtifactRank.TREASURE ), 1 );
        assertEquals( ArtifactRank.MINOR.compareRanks( ArtifactRank.MAJOR ), -1 );
    }
}