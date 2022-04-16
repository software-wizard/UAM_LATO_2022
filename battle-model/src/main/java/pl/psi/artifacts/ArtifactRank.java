package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum ArtifactRank
{
    TREASURE( "TREASURE", 0 ),

    MINOR( "MINOR",1 ),

    MAJOR( "MAJOR", 2 ),

    RELIC( "RELIC", 3 );

    private final String name;

    private final int rank;

    /**
     * Compares artifact rank with other artifact's rank.
     *
     * @param aComparedRank rank to compare with.
     * @return 0 - if ranks are the same, 1 if rank that you compare is higher and finally -1 if rank that you compare
     *         with is lower.
     */
    public int compareRanks( ArtifactRank aComparedRank )
    {
        return Integer.compare( this.getRank(), aComparedRank.getRank() );
    }
}
