package pl.psi.artifacts;

/**
 * Dedicated exception for artifacts when they fail validation.
 */
class ArtifactValidationException extends RuntimeException
{
    public ArtifactValidationException( String aMessage )
    {
        super( aMessage );
    }
}
